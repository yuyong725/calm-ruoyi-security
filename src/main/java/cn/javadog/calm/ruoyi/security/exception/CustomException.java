package cn.javadog.calm.ruoyi.security.exception;

import lombok.Getter;

/**
 * @author 余勇
 * @date 2020-01-04 21:42
 * 自定义异常
 */
@Getter
public class CustomException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private Integer code;

    private String message;

    public CustomException(String message) {
        this.message = message;
    }

    public CustomException(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public CustomException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

}
