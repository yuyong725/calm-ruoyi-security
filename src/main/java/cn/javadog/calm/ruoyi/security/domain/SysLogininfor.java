package cn.javadog.calm.ruoyi.security.domain;

import java.util.Date;

import lombok.Data;

/**
 * @author 余勇
 * @date 2019-12-30 17:33
 * 系统访问记录表 sys_logininfor
 */
@Data
public class SysLogininfor extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** ID */
    private Long infoId;

    /** 用户账号 */
    private String userName;

    /** 登录状态 0成功 1失败 */
    private String status;

    /** 登录IP地址 */
    private String ipaddr;

    /** 登录地点 */
    private String loginLocation;

    /** 浏览器类型 */
    private String browser;

    /** 操作系统 */
    private String os;

    /** 提示消息 */
    private String msg;

    /** 访问时间 */
    private Date loginTime;

}