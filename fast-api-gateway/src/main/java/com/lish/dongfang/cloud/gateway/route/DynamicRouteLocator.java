package com.lish.dongfang.cloud.gateway.route;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;
import org.springframework.util.StringUtils;

import com.lish.dongfang.cloud.gateway.db.DBHelper;
import com.lish.dongfang.cloud.gateway.db.DBHelper.ZuulRouteVO;

/**
 * 自动路由配置加载类
 * @author lisong
 *
 */
public class DynamicRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {

	public final static Logger logger = LoggerFactory.getLogger(DynamicRouteLocator.class);

	private ZuulProperties properties;
	
	@Autowired
	private DBHelper dbHelper;

	public DynamicRouteLocator(String servletPath, ZuulProperties properties) {
		super(servletPath, properties);
        this.properties = properties;
        logger.info("servletPath:{}",servletPath);
	}

	@Override
	public void refresh() {
		doRefresh();
	}

	@Override
	protected Map<String, ZuulRoute> locateRoutes() {
		logger.info("=====调用自定义AutoRouteLocator=============");
		LinkedHashMap<String, ZuulRoute> routesMap = new LinkedHashMap<String, ZuulRoute>();
		// 从application.properties中加载路由信息
		routesMap.putAll(super.locateRoutes());
		// 从db中加载路由信息
		routesMap.putAll(locateRoutesFromDB());
		// 优化一下配置
		LinkedHashMap<String, ZuulRoute> values = new LinkedHashMap<>();
		for (Map.Entry<String, ZuulRoute> entry : routesMap.entrySet()) {
			String path = entry.getKey();
			// Prepend with slash if not already present.
			if (!path.startsWith("/")) {
				path = "/" + path;
			}
			if (StringUtils.hasText(this.properties.getPrefix())) {
				path = this.properties.getPrefix() + path;
				if (!path.startsWith("/")) {
					path = "/" + path;
				}
			}
			values.put(path, entry.getValue());
		}
		return values;
	}

	private Map<String, ZuulRoute> locateRoutesFromDB() {
		logger.info("=====加载数据库中的路由信息=============");
		Map<String, ZuulRoute> routes = new LinkedHashMap<>();
		List<ZuulRouteVO> results = dbHelper.getAllRoutes();
		for (ZuulRouteVO result : results) {
			if (org.apache.commons.lang3.StringUtils.isBlank(result.getPath())
					|| org.apache.commons.lang3.StringUtils.isBlank(result.getUrl())) {
				continue;
			}
			ZuulRoute zuulRoute = new ZuulRoute();
			try {
				org.springframework.beans.BeanUtils.copyProperties(result, zuulRoute);
			} catch (Exception e) {
				logger.error("=============load zuul route info from db with error==============", e);
			}
			routes.put(zuulRoute.getPath(), zuulRoute);
		}
		return routes;
	}
}
