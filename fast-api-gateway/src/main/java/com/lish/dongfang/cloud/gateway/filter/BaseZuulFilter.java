package com.lish.dongfang.cloud.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lish.dongfang.cloud.gateway.db.DBHelper;
import com.netflix.zuul.ZuulFilter;

public abstract class BaseZuulFilter extends ZuulFilter {
	
	private static Logger logger = LoggerFactory.getLogger(BaseZuulFilter.class);
	
	protected DBHelper dbHelper;
	
	/**
	 *  pre：可以在请求被路由之前调用。
	 * 	routing：在路由请求时候被调用。
	 *	post：在routing和error过滤器之后被调用。
	 *	error：处理请求时发生错误时被调用。
	 * @author lisong
	 *
	 */
	protected enum FILTER_TYPE{
		pre,routing,post,error
	}

	public BaseZuulFilter() {
		super();
	}
	
	public BaseZuulFilter(DBHelper dbHelper) {
		super();
		this.dbHelper=dbHelper;
	}

	@Override
	public boolean shouldFilter() {
		logger.info("调用自定义过滤器shouldFilter方法，返回true");
		return true;
	}

	@Override
	public abstract Object run();

	@Override
	public abstract int filterOrder();
	
	

}