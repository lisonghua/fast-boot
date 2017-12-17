package com.lish.dongfang.cloud.gateway.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lish.dongfang.cloud.gateway.route.AutoRouteLocator;

/**
 * 自动路由配置
 * 
 * @author lisong
 *
 */
@Configuration
public class AutoRouterConfiguration {
	@Autowired
	ZuulProperties zuulProperties;
	@Autowired
	ServerProperties server;
	
	@Bean
	public AutoRouteLocator routeLocator() {
		AutoRouteLocator routeLocator = new AutoRouteLocator(this.server.getServletPrefix(), this.zuulProperties);
		return routeLocator;
	}
}
