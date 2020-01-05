package cn.javadog.calm.ruoyi.security.service;

import java.util.Set;

import cn.javadog.calm.ruoyi.security.domain.SysUser;

/**
 * @author 余勇
 * @date 2019年12月30日 17:07:00
 */
public interface ISysPermissionService {
	Set<String> getRolePermission(SysUser user);

	Set<String> getMenuPermission(SysUser user);
}
