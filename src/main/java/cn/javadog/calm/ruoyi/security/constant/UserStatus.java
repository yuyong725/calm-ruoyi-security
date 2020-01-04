package cn.javadog.calm.ruoyi.security.constant;

/**
 * @author 余勇
 * @date 2019-12-30 19:54
 *
 * 用户状态
 */
public enum UserStatus {

    /**
     * 正常
     */
    OK("0", "正常"),

    /**
     * 停用
     */
    DISABLE("1", "停用"),

    /**
     * 删除
     */
    DELETED("2", "删除");

    private final String code;
    private final String info;

    UserStatus(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }
}
