package com.lish.dongfang.cloud.gateway.filter;

public abstract class RoutingFilter extends BaseZuulFilter {

	@Override
	public String filterType() {
		return FILTER_TYPE.routing.name();
	}

}
