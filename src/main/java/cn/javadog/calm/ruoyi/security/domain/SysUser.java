package cn.javadog.calm.ruoyi.security.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Transient;

/**
 * @author 余勇
 * @date 2019年12月30日 15:55:00
 *
 * 用户实体类
 */
@Data
public class SysUser extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 用户ID */
	private Long userId;

	/** 部门ID */
	private Long deptId;

	/** 登录名称 */
	private String userName;

	/** 用户名称 */
	private String nickName;

	/** 用户邮箱 */
	private String email;

	/** 手机号码 */
	private String phonenumber;

	/** 用户性别 (0=男,1=女,2=未知) */
	private String sex;

	/** 用户头像 */
	private String avatar;

	/** 密码 */
	private String password;

	/** 盐加密 */
	private String salt;

	/** 帐号状态 (0=正常,1=停用) */
	private String status;

	/** 删除标志（0代表存在 2代表删除） */
	private String delFlag;

	/** 最后登陆IP */
	private String loginIp;

	/** 最后登陆时间 */
	private Date loginDate;

	/** 角色对象 */
	@Transient
	private List<SysRole> roles;

	/** 部门对象 */
	private SysDept dept;

	/** 角色组 */
	@Transient
	private Long[] roleIds;

	/** 岗位组 */
	@Transient
	private Long[] postIds;

	public SysUser() { }

	public SysUser(Long userId)
	{
		this.userId = userId;
	}

	public boolean isAdmin()
	{
		return isAdmin(this.userId);
	}

	public static boolean isAdmin(Long userId)
	{
		return userId != null && 1L == userId;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("userId", getUserId())
			.append("deptId", getDeptId())
			.append("userName", getUserName())
			.append("nickName", getNickName())
			.append("email", getEmail())
			.append("phonenumber", getPhonenumber())
			.append("sex", getSex())
			.append("avatar", getAvatar())
			.append("password", getPassword())
			.append("salt", getSalt())
			.append("status", getStatus())
			.append("delFlag", getDelFlag())
			.append("loginIp", getLoginIp())
			.append("loginDate", getLoginDate())
			.append("createBy", getCreateBy())
			.append("createTime", getCreateTime())
			.append("updateBy", getUpdateBy())
			.append("updateTime", getUpdateTime())
			.append("remark", getRemark())
			.append("dept", getDept())
			.toString();
	}

}
