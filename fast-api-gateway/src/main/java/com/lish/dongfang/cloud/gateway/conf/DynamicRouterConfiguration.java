package com.lish.dongfang.cloud.gateway.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lish.dongfang.cloud.gateway.route.DynamicRouteLocator;

/**
 * 自动路由配置
 * 
 * @author lisong
 *
 */
@Configuration
public class DynamicRouterConfiguration {
	@Autowired
	ZuulProperties zuulProperties;
	@Autowired
	ServerProperties server;
	
	@Bean
	public DynamicRouteLocator routeLocator() {
		DynamicRouteLocator routeLocator = new DynamicRouteLocator(this.server.getServletPrefix(), this.zuulProperties);
		return routeLocator;
	}
	
	
}
