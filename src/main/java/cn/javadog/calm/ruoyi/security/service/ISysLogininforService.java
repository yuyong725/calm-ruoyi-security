package cn.javadog.calm.ruoyi.security.service;

import java.util.List;

import cn.javadog.calm.ruoyi.security.domain.SysLogininfor;

/**
 * @author 余勇
 * @date 2019年12月30日 17:49:00
 */
public interface ISysLogininforService {

	/**
	 * 新增系统登录日志
	 *
	 * @param logininfor 访问日志对象
	 */
	public void insertLogininfor(SysLogininfor logininfor);

	/**
	 * 查询系统登录日志集合
	 *
	 * @param logininfor 访问日志对象
	 * @return 登录记录集合
	 */
	public List<SysLogininfor> selectLogininforList(SysLogininfor logininfor);

	/**
	 * 批量删除系统登录日志
	 *
	 * @param infoIds 需要删除的登录日志ID
	 * @return
	 */
	public int deleteLogininforByIds(Long[] infoIds);

	/**
	 * 清空系统登录日志
	 */
	public void cleanLogininfor();

}
