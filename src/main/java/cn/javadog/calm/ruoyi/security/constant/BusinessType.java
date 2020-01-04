package cn.javadog.calm.ruoyi.security.constant;

/**
 * @author 余勇
 * @date 2020-01-04 21:17
 *
 * 业务操作类型
 */
public enum BusinessType {
    /**
     * 其它
     */
    OTHER,

    /**
     * 新增
     */
    INSERT,

    /**
     * 修改
     */
    UPDATE,

    /**
     * 删除
     */
    DELETE,

    /**
     * 授权
     */
    GRANT,

    /**
     * 导出
     */
    EXPORT,

    /**
     * 导入
     */
    IMPORT,

    /**
     * 强退
     */
    FORCE,

    /**
     * 生成代码
     */
    GENCODE,
    
    /**
     * 清空数据
     */
    CLEAN,
}
