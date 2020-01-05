package cn.javadog.calm.ruoyi.security.controller;

import java.util.List;
import java.util.Set;

import cn.javadog.calm.ruoyi.security.constant.Constants;
import cn.javadog.calm.ruoyi.security.domain.SysMenu;
import cn.javadog.calm.ruoyi.security.domain.SysUser;
import cn.javadog.calm.ruoyi.security.service.ISysLoginService;
import cn.javadog.calm.ruoyi.security.service.ISysMenuService;
import cn.javadog.calm.ruoyi.security.service.SysPermissionService;
import cn.javadog.calm.ruoyi.security.service.TokenService;
import cn.javadog.calm.ruoyi.security.utils.AjaxResult;
import cn.javadog.calm.ruoyi.security.domain.LoginUser;
import cn.javadog.calm.ruoyi.security.utils.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 余勇
 * @date 2019年12月30日 16:23:00
 */
@RestController
public class SysLoginController {

	@Autowired
	private ISysLoginService loginService;

	@Autowired
	private ISysMenuService menuService;

	@Autowired
	private SysPermissionService permissionService;

	@Autowired
	private TokenService tokenService;

	/**
	 * 登录方法
	 *
	 * @param username 用户名
	 * @param password 密码
	 * @param code 验证码
	 * @param uuid 唯一标识
	 * @return 结果
	 */
	@PostMapping("/login")
	public AjaxResult login(String username, String password, String code, String uuid) {
		AjaxResult ajax = AjaxResult.success();
		// 生成令牌
		String token = loginService.login(username, password, code, uuid);
		ajax.put(Constants.TOKEN, token);
		return ajax;
	}

	/**
	 * 获取用户信息
	 *
	 * @return 用户信息
	 */
	@GetMapping("getInfo")
	public AjaxResult getInfo() {
		LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
		SysUser user = loginUser.getUser();
		// 角色集合
		Set<String> roles = permissionService.getRolePermission(user);
		// 权限集合
		Set<String> permissions = permissionService.getMenuPermission(user);
		AjaxResult ajax = AjaxResult.success();
		ajax.put("user", user);
		ajax.put("roles", roles);
		ajax.put("permissions", permissions);
		return ajax;
	}

	/**
	 * 获取路由信息
	 */
	@GetMapping("getRouters")
	public AjaxResult getRouters() {
		LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
		// 用户信息
		SysUser user = loginUser.getUser();
		List<SysMenu> menus = menuService.selectMenuTreeByUserId(user.getUserId());
		return AjaxResult.success(menuService.buildMenus(menus));
	}
}
