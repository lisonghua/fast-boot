package com.lish.dongfang.vote.ms.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lish.dongfang.core.adapter.SwaggerAdapter;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;

/**
 * 服务配置
 * @author lisong
 *
 */
@Configuration
public class ServiceConfiguration {
	
	private static Logger logger = LoggerFactory.getLogger(ServiceConfiguration.class);
	
	/**
	 * 添加扫描路径
	 * @return
	 */
	@Bean
	public SwaggerAdapter swaggerAdapter() {
		logger.info("-------注入SwaggerAdapter---------------");
		return new SwaggerAdapter() {
			@Override
			public ApiSelectorBuilder addScanBasePackages(ApiSelectorBuilder builder) {
				logger.info("-------添加swagger扫描目录---------------");
				return builder.apis(RequestHandlerSelectors.basePackage("com.lish.dongfang.vote.ms"));
			}
		};
	}
}
