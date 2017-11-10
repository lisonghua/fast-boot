package com.lish.dongfang.cloud.gateway.filter;

public abstract class PostFilter extends BaseZuulFilter {

	@Override
	public String filterType() {
		return FILTER_TYPE.post.name();
	}

}
