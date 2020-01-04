package cn.javadog.calm.ruoyi.security.exception;

/**
 * @author 余勇
 * @date 2020-01-04 21:41
 *
 * 验证码错误异常类
 */
public class CaptchaException extends UserException {
    private static final long serialVersionUID = 1L;

    public CaptchaException() {
        super("user.jcaptcha.error", null);
    }
}
