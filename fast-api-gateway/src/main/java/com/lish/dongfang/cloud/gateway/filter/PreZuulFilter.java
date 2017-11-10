package com.lish.dongfang.cloud.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class PreZuulFilter extends BaseZuulFilter {
	
	private static Logger logger = LoggerFactory.getLogger(PreZuulFilter.class);
	
	@Override
	public String filterType() {
		logger.info("当前注册的过滤器类型：pre");
		return FILTER_TYPE.pre.name();
	}
}
