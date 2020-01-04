package cn.javadog.calm.ruoyi.security.config;

import cn.javadog.calm.ruoyi.security.filter.JwtAuthenticationTokenFilter;
import cn.javadog.calm.ruoyi.security.handler.AuthenticationEntryPointImpl;
import cn.javadog.calm.ruoyi.security.handler.LogoutSuccessHandlerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author 余勇
 * @date 2019年12月30日 16:20:00
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * 自定义用户认证逻辑
	 */
	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * 认证失败处理类
	 */
	@Autowired
	private AuthenticationEntryPointImpl unauthorizedHandler;

	/**
	 * 退出处理类
	 */
	@Autowired
	private LogoutSuccessHandlerImpl logoutSuccessHandler;

	/**
	 * token 认证过滤器
	 */
	@Autowired
	private JwtAuthenticationTokenFilter authenticationTokenFilter;

	/**
	 * 身份认证接口
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			// CRSF禁用，因为不使用session
			.csrf().disable()
			// 认证失败处理类
			.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
			// 基于token，所以不需要session
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			// 过滤请求
			.authorizeRequests()
			// 对于登录login 验证码captchaImage 允许匿名访问
			.antMatchers("/login", "/captchaImage").anonymous()
			.antMatchers(
				HttpMethod.GET,
				"/*.html",
				"/**/*.html",
				"/**/*.css",
				"/**/*.js"
			).permitAll()
			.antMatchers("/profile/**").anonymous()
			.antMatchers("/swagger-ui.html").anonymous()
			.antMatchers("/swagger-resources/**").anonymous()
			.antMatchers("/webjars/**").anonymous()
			.antMatchers("/*/api-docs").anonymous()
			.antMatchers("/druid/**").anonymous()
			// 除上面外的所有请求全部需要鉴权认证
			.anyRequest().authenticated()
			.and()
			.headers().frameOptions().disable();
		httpSecurity.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler);
		// 添加 JWT filter
		httpSecurity.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
	}


	/**
	 * 强散列哈希加密实现
	 */
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}


}
