package cn.javadog.calm.ruoyi.security.service;

/**
 * @author 余勇
 * @date 2019年12月30日 17:01:00
 */
public interface ISysLoginService {

	public String login(String username, String password, String code, String uuid);

}
