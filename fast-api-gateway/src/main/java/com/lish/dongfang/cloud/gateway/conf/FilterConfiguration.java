package com.lish.dongfang.cloud.gateway.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lish.dongfang.cloud.gateway.filter.AccessControlFilter;

/**
 * 过滤器配置类
 * @author lisong
 *
 */
@Configuration
public class FilterConfiguration {
	
	/**
	 * 注册过滤器
	 * @return
	 */
	@Bean
	AccessControlFilter accessControlFilter() {
		return new AccessControlFilter();
	}
}
