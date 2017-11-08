package com.sj.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.lish.dongfang.core.web.interceptor.ControllerInterceptor;

/**
 * 全局controller日志打印配置(暂时关闭)
 * @author lisong
 *
 */
//@Configuration
public class LoggerConfiguration extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new ControllerInterceptor()).addPathPatterns("/**");
	}
}
