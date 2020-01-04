package cn.javadog.calm.ruoyi.security.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

import cn.javadog.calm.ruoyi.security.constant.HttpStatus;
import cn.javadog.calm.ruoyi.security.utils.AjaxResult;
import cn.javadog.calm.ruoyi.security.utils.ServletUtils;
import cn.javadog.calm.ruoyi.security.utils.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;

/**
 * @author 余勇
 * @date 2020-01-04 21:45
 *
 * 认证失败处理类 返回未授权
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        int code = HttpStatus.UNAUTHORIZED;
        String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资源", request.getRequestURI());
        ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.error(code, msg)));
    }
}
