package com.lish.dongfang.cloud.gateway.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.event.HeartbeatEvent;
import org.springframework.cloud.client.discovery.event.HeartbeatMonitor;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.lish.dongfang.cloud.gateway.cache.BlacklistCache;

/**
 * 黑名单刷新事件
 * @author lisong
 *
 */
public class BlacklistRefreshListener implements ApplicationListener<ApplicationEvent> {
	
	@Autowired
	private BlacklistCache blacklistCache;
	
	private HeartbeatMonitor heartbeatMonitor = new HeartbeatMonitor();

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		//监听手工发送BlacklistCacheRefreshEven事件
		if (event instanceof ContextRefreshedEvent
				|| event instanceof RefreshScopeRefreshedEvent
				|| event instanceof BlacklistCacheRefreshEven) {
			blacklistCache.refresh();
		}
		//监听eureka服务发送心跳事件（集群环境）
		else if (event instanceof HeartbeatEvent) {
			if (this.heartbeatMonitor.update(((HeartbeatEvent) event).getValue())) {
				blacklistCache.refresh();
			}
		}
	}
}
