package cn.javadog.calm.ruoyi.security.handler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import cn.javadog.calm.ruoyi.security.constant.Constants;
import cn.javadog.calm.ruoyi.security.constant.HttpStatus;
import cn.javadog.calm.ruoyi.security.utils.AjaxResult;
import cn.javadog.calm.ruoyi.security.domain.LoginUser;
import cn.javadog.calm.ruoyi.security.task.AsyncFactory;
import cn.javadog.calm.ruoyi.security.task.AsyncManager;
import cn.javadog.calm.ruoyi.security.utils.ServletUtils;
import cn.javadog.calm.ruoyi.security.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import com.alibaba.fastjson.JSON;

/**
 * @author 余勇
 * @date 2020-01-04 21:47
 *
 * 自定义退出处理类 返回成功
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    @Autowired
    private TokenService tokenService;

    /**
     * 退出处理
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser)) {
            String userName = loginUser.getUsername();
            // 删除用户缓存记录
            tokenService.delLoginUser(loginUser.getToken());
            // 记录用户退出日志
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(userName, Constants.LOGOUT, "退出成功"));
        }
        ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.error(HttpStatus.SUCCESS, "退出成功")));
    }
}
