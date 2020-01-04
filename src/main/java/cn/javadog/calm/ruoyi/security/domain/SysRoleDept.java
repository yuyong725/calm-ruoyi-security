package cn.javadog.calm.ruoyi.security.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author 余勇
 * @date 2020-01-04 21:28
 *
 * 角色和部门关联 sys_role_dept
 */
@Data
public class SysRoleDept {

    /** 角色ID */
    private Long roleId;
    
    /** 部门ID */
    private Long deptId;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("roleId", getRoleId())
            .append("deptId", getDeptId())
            .toString();
    }
}
