package cn.javadog.calm.ruoyi.security.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author 余勇
 * @date 2019-12-30 17:35
 * Entity基类
 */
@Data
public class BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 搜索值 */
    private String searchValue;

    /** 创建者 */
    private String createBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /** 备注 */
    private String remark;

    /** 数据权限 */
    private String dataScope;

    /** 开始时间 */
    @JsonIgnore
    private String beginTime;

    /** 结束时间 */
    @JsonIgnore
    private String endTime;

    /** 请求参数 */
    private Map<String, Object> params;

    public Map<String, Object> getParams() {
        if (params == null) {
            params = new HashMap<>();
        }
        return params;
    }

}
