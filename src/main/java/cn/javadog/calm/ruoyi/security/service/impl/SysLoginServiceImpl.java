package cn.javadog.calm.ruoyi.security.service.impl;

import javax.annotation.Resource;

import cn.javadog.calm.ruoyi.security.constant.Constants;
import cn.javadog.calm.ruoyi.security.exception.CaptchaException;
import cn.javadog.calm.ruoyi.security.exception.CaptchaExpireException;
import cn.javadog.calm.ruoyi.security.exception.CustomException;
import cn.javadog.calm.ruoyi.security.exception.UserPasswordNotMatchException;
import cn.javadog.calm.ruoyi.security.redis.RedisCache;
import cn.javadog.calm.ruoyi.security.service.ISysLoginService;
import cn.javadog.calm.ruoyi.security.service.TokenService;
import cn.javadog.calm.ruoyi.security.task.AsyncFactory;
import cn.javadog.calm.ruoyi.security.task.AsyncManager;
import cn.javadog.calm.ruoyi.security.domain.LoginUser;
import cn.javadog.calm.ruoyi.security.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * @author 余勇
 * @date 2019年12月30日 16:24:00
 */
@Service
public class SysLoginServiceImpl implements ISysLoginService {

	@Autowired
	private TokenService tokenService;

	@Resource
	private AuthenticationManager authenticationManager;

	@Autowired
	private RedisCache redisCache;

	/**
	 * 登录验证
	 *
	 * @param username 用户名
	 * @param password 密码
	 * @param code     验证码
	 * @param uuid     唯一标识
	 * @return 结果
	 */
	@Override
	public String login(String username, String password, String code, String uuid) {
		// 验证图片验证码的正确性
		// uuid 的作用，是获得对应的图片验证码
		String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
		// 从 Redis 中，获得图片验证码
		String captcha = redisCache.getCacheObject(verifyKey);
		// 从 Redis 中，删除图片验证码
		redisCache.deleteObject(verifyKey);
		// 图片验证码不存在
		if (captcha == null) {
			AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
			throw new CaptchaExpireException();
		}
		// 图片验证码不正确
		if (!code.equalsIgnoreCase(captcha)) {
			AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));
			throw new CaptchaException();
		}
		// 用户验证
		Authentication authentication;
		try {
			// 该方法会去调用 UserDetailsServiceImpl.loadUserByUsername
			authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (Exception e) {
			// 发生异常，说明验证不通过，记录相应的登陆失败日志
			if (e instanceof BadCredentialsException) {
				AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
				throw new UserPasswordNotMatchException();
			} else {
				AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
				throw new CustomException(e.getMessage());
			}
		}
		// 验证通过，记录相应的登陆成功日志
		AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
		// 生成 Token
		LoginUser loginUser = (LoginUser) authentication.getPrincipal();
		return tokenService.createToken(loginUser);
	}

}
