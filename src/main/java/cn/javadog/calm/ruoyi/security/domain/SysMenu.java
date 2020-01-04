package cn.javadog.calm.ruoyi.security.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import org.springframework.data.annotation.Transient;

/**
 * @author 余勇
 * @date 2019年12月30日 16:18:00
 */
@Data
public class SysMenu extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 菜单ID */
	private Long menuId;

	/** 菜单名称 */
	private String menuName;

	/** 父菜单名称 */
	private String parentName;

	/** 父菜单ID */
	private Long parentId;

	/** 显示顺序 */
	private String orderNum;

	/** 路由地址 */
	private String path;

	/** 组件路径 */
	private String component;

	/** 是否为外链（0是 1否） */
	private String isFrame;

	/** 类型（M目录 C菜单 F按钮） */
	private String menuType;

	/** 菜单状态:0显示,1隐藏 */
	private String visible;

	/** 权限字符串 */
	private String perms;

	/** 菜单图标 */
	private String icon;

	/** 子菜单 */
	@Transient
	private List<SysMenu> children = new ArrayList<SysMenu>();
}
