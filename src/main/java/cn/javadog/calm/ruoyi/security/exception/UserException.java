package cn.javadog.calm.ruoyi.security.exception;

/**
 * @author 余勇
 * @date 2020-01-04 21:43
 *
 * 用户信息异常类
 */
public class UserException extends BaseException {

    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args) {
        super("user", code, args, null);
    }
}
