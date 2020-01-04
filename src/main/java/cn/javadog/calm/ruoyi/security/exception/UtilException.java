package cn.javadog.calm.ruoyi.security.exception;

/**
 * @author 余勇
 * @date 2020-01-04 21:44
 *
 * 工具类异常
 */
public class UtilException extends RuntimeException {
    private static final long serialVersionUID = 8247610319171014183L;

    public UtilException(Throwable e) {
        super(e.getMessage(), e);
    }

    public UtilException(String message) {
        super(message);
    }

    public UtilException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
