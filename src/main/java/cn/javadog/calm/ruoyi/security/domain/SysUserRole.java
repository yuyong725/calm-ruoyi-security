package cn.javadog.calm.ruoyi.security.domain;

import lombok.Data;

/**
 * @author 余勇
 * @date 2019年12月30日 16:17:00
 *
 * 用户角色对应关系
 */
@Data
public class SysUserRole {

	/** 用户ID */
	private Long userId;

	/** 角色ID */
	private Long roleId;

}
