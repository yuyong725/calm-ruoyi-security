package cn.javadog.calm.ruoyi.security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author 余勇
 * @date 2019年12月30日 15:53:00
 * 启动类 表示通过aop框架暴露该代理对象,AopContext能够访问
 * exposeProxy
 */
@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
@MapperScan("cn.javadog.calm.ruoyi.security.mapper")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
