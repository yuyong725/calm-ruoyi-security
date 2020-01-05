package cn.javadog.calm.ruoyi.security.service.impl;

import java.util.HashSet;
import java.util.Set;

import cn.javadog.calm.ruoyi.security.domain.SysUser;
import cn.javadog.calm.ruoyi.security.service.ISysMenuService;
import cn.javadog.calm.ruoyi.security.service.ISysPermissionService;
import cn.javadog.calm.ruoyi.security.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 余勇
 * @date 2019年12月30日 17:07:00
 */
@Service
public class SysPermissionServiceImpl implements ISysPermissionService {

	@Autowired
	private ISysRoleService roleService;

	@Autowired
	private ISysMenuService menuService;

	/**
	 * 获取角色数据权限
	 *
	 * @param user 用户信息
	 * @return 角色权限信息
	 */
	@Override
	public Set<String> getRolePermission(SysUser user) {
		Set<String> roles = new HashSet<String>();
		// 管理员拥有所有权限
		if (user.isAdmin()) {
			roles.add("admin");
		} else {
			roles.addAll(roleService.selectRolePermissionByUserId(user.getUserId()));
		}
		return roles;
	}

	/**
	 * 获取菜单数据权限
	 *
	 * @param user 用户信息
	 * @return 菜单权限信息
	 */
	@Override
	public Set<String> getMenuPermission(SysUser user) {
		Set<String> roles = new HashSet<String>();
		// 管理员拥有所有权限
		if (user.isAdmin()) {
			roles.add("*:*:*");
		} else {
			roles.addAll(menuService.selectMenuPermsByUserId(user.getUserId()));
		}
		return roles;
	}

}
