package cn.javadog.calm.ruoyi.security.service;

import javax.servlet.http.HttpServletRequest;

import cn.javadog.calm.ruoyi.security.domain.LoginUser;

/**
 * @author 余勇
 * @date 2019年12月30日 17:11:00
 */
public interface TokenService {
	LoginUser getLoginUser(HttpServletRequest request);

	void setLoginUser(LoginUser loginUser);

	void delLoginUser(String token);

	String createToken(LoginUser loginUser);

	void verifyToken(LoginUser loginUser);

	void refreshToken(LoginUser loginUser);

	void setUserAgent(LoginUser loginUser);

	String getUsernameFromToken(String token);
}
