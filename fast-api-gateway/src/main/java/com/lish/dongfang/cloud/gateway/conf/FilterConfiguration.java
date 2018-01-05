package com.lish.dongfang.cloud.gateway.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lish.dongfang.cloud.gateway.db.DBHelper;
import com.lish.dongfang.cloud.gateway.filter.AccessControlFilter;

/**
 * 过滤器配置类
 * @author lisong
 *
 */
@Configuration
public class FilterConfiguration {
	
	@Autowired
	private DBHelper dbHelper;
	
	/**
	 * 注册过滤器
	 * @return
	 */
	@Bean
	@ConditionalOnProperty("fast.gateway.backlist.enable")
	AccessControlFilter accessControlFilter(DBHelper dbHelper) {
		return new AccessControlFilter(dbHelper);
	}
}
