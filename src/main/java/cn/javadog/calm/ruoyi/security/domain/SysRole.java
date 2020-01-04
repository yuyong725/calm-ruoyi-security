package cn.javadog.calm.ruoyi.security.domain;


import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Transient;

/**
 * @author 余勇
 * @date 2019年12月30日 16:03:00
 *
 * 角色实体类
 */
@Data
public class SysRole extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 角色序号 */
	private Long roleId;

	/** 角色名称 */
	private String roleName;

	/** 角色权限 */
	private String roleKey;

	/** 角色排序 */
	private String roleSort;

	/** 数据范围（1=所有数据权限,2=自定义数据权限,3=本部门数据权限,4=本部门及以下数据权限） */
	private String dataScope;

	/** 角色状态（0=正常,1=停用） */
	private String status;

	/** 删除标志（0代表存在 2代表删除） */
	private String delFlag;

	/** 用户是否存在此角色标识 默认不存在 */
	@Transient
	private boolean flag = false;

	/** 菜单组 */
	@Transient
	private Long[] menuIds;

	/** 部门组（数据权限） */
	@Transient
	private Long[] deptIds;

	public SysRole() { }

	public SysRole(Long roleId)
	{
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("roleId", getRoleId())
			.append("roleName", getRoleName())
			.append("roleKey", getRoleKey())
			.append("roleSort", getRoleSort())
			.append("dataScope", getDataScope())
			.append("status", getStatus())
			.append("delFlag", getDelFlag())
			.append("createBy", getCreateBy())
			.append("createTime", getCreateTime())
			.append("updateBy", getUpdateBy())
			.append("updateTime", getUpdateTime())
			.append("remark", getRemark())
			.toString();
	}

	public boolean isAdmin()
	{
		return isAdmin(this.roleId);
	}

	public static boolean isAdmin(Long roleId)
	{
		return roleId != null && 1L == roleId;
	}
}
