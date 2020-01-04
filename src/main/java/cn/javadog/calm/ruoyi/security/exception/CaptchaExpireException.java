package cn.javadog.calm.ruoyi.security.exception;


/**
 * @author 余勇
 * @date 2020-01-04 21:42
 *
 * 验证码失效异常类
 */
public class CaptchaExpireException extends UserException {

    private static final long serialVersionUID = 1L;

    public CaptchaExpireException() {
        super("user.jcaptcha.expire", null);
    }
}
