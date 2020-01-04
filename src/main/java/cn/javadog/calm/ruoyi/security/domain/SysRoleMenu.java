package cn.javadog.calm.ruoyi.security.domain;

import lombok.Data;

/**
 * @author 余勇
 * @date 2019年12月30日 16:19:00
 *
 * 角色菜单对应关系
 */
@Data
public class SysRoleMenu {

	/** 角色ID */
	private Long roleId;

	/** 菜单ID */
	private Long menuId;

}
