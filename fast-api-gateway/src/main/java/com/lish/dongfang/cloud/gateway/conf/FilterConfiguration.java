package com.lish.dongfang.cloud.gateway.conf;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lish.dongfang.cloud.gateway.cache.BlacklistCache;
import com.lish.dongfang.cloud.gateway.event.BlacklistRefreshListener;
import com.lish.dongfang.cloud.gateway.filter.AccessControlFilter;

/**
 * 过滤器配置类
 * @author lisong
 *
 */
@Configuration
public class FilterConfiguration {
	
	/**
	 * 注册黑名单缓存
	 * @return
	 */
	@Bean
	BlacklistCache blacklistCache() {
		return new BlacklistCache();
	}
	
	/**
	 * 注册过滤器
	 * @return
	 */
	@Bean
	@ConditionalOnProperty("fast.gateway.backlist.enable")
	AccessControlFilter accessControlFilter() {
		return new AccessControlFilter();
	}
	
	/**
	 * 注册黑名单缓存刷新监听
	 * @return
	 */
	@Bean
	BlacklistRefreshListener blacklistRefreshListener() {
		return new BlacklistRefreshListener();
	}
}
