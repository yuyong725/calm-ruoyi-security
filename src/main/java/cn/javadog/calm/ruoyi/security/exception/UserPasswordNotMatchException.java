package cn.javadog.calm.ruoyi.security.exception;

/**
 * @author 余勇
 * @date 2020-01-04 21:44
 *
 * 用户密码不正确或不符合规范异常类
 */
public class UserPasswordNotMatchException extends UserException {

    private static final long serialVersionUID = 1L;

    public UserPasswordNotMatchException() {
        super("user.password.not.match", null);
    }
}
