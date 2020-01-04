package cn.javadog.calm.ruoyi.security.mapper;

import java.util.List;

import cn.javadog.calm.ruoyi.security.domain.SysRoleMenu;

/**
 * @author 余勇
 * @date 2020-01-04 21:50
 *
 * 角色与菜单关联表 数据层
 */
public interface SysRoleMenuMapper {
    /**
     * 查询菜单使用数量
     * 
     * @param menuId 菜单ID
     * @return 结果
     */
    public int checkMenuExistRole(Long menuId);

    /**
     * 通过角色ID删除角色和菜单关联
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    public int deleteRoleMenuByRoleId(Long roleId);

    /**
     * 批量新增角色菜单信息
     * 
     * @param roleMenuList 角色菜单列表
     * @return 结果
     */
    public int batchRoleMenu(List<SysRoleMenu> roleMenuList);
}
