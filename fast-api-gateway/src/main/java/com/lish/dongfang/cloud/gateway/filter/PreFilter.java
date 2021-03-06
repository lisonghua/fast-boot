package com.lish.dongfang.cloud.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class PreFilter extends BaseZuulFilter {
	
	private static Logger logger = LoggerFactory.getLogger(PreFilter.class);
	
	
	public PreFilter() {
		super();
	}


	@Override
	public String filterType() {
		logger.info("当前注册的过滤器类型：pre");
		return FILTER_TYPE.pre.name();
	}
}
