package com.lish.dongfang.cloud.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lish.dongfang.cloud.gateway.db.DBHelper;

public abstract class PreFilter extends BaseZuulFilter {
	
	private static Logger logger = LoggerFactory.getLogger(PreFilter.class);
	
	public PreFilter(DBHelper dbHelper) {
		super(dbHelper);
	}
	
	
	public PreFilter() {
		super();
	}


	@Override
	public String filterType() {
		logger.info("当前注册的过滤器类型：pre");
		return FILTER_TYPE.pre.name();
	}
}
