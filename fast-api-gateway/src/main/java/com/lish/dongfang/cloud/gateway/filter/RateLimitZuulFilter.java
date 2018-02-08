package com.lish.dongfang.cloud.gateway.filter;

import org.springframework.core.Ordered;

import com.google.common.util.concurrent.RateLimiter;

/**
 * 限流过滤器，采用google实现的令牌桶算法（目前未实现）
 * @author lisong
 *
 */
public class RateLimitZuulFilter extends PreFilter {
	
	private final RateLimiter rateLimiter = RateLimiter.create(1000.0);

	@Override
	public Object run() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int filterOrder() {
		return Ordered.HIGHEST_PRECEDENCE;//优先级最高
	}
}
