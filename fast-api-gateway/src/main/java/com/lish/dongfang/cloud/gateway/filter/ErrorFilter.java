package com.lish.dongfang.cloud.gateway.filter;

public abstract class ErrorFilter extends BaseZuulFilter {

	@Override
	public String filterType() {
		return FILTER_TYPE.error.name();
	}

}
