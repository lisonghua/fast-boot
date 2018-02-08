package com.lish.dongfang.cloud.gateway.event;

import org.springframework.context.ApplicationEvent;

import com.lish.dongfang.cloud.gateway.cache.BlacklistCache;

public class BlacklistCacheRefreshEven extends ApplicationEvent {

	public BlacklistCacheRefreshEven(BlacklistCache blackListCache) {
		super(blackListCache);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
