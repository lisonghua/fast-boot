package com.lish.dongfang.cloud.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * api网关处理请求时执行的过滤器
 * @author lisong
 *
 */
public class AccessControlFilter extends PreZuulFilter {
	
	private static Logger logger = LoggerFactory.getLogger(AccessControlFilter.class);

	@Override
	public Object run() {
		logger.info("----------执行pre过滤器逻辑-----------");
		return null;
	}

	/* 
	 * 通过int值来定义过滤器的执行顺序，数值越小优先级越高
	 * (non-Javadoc)
	 * @see com.netflix.zuul.ZuulFilter#filterOrder()
	 */
	@Override
	public int filterOrder() {
		return 0;
	}
}
