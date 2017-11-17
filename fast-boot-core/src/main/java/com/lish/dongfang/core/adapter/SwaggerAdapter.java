package com.lish.dongfang.core.adapter;

import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;

/**
 * 使用swagger时自定义配置适配器
 * @author lisong
 *
 */
public abstract class SwaggerAdapter {
	/**
	 * 添加扫描路径
	 * @param builder
	 * @return
	 */
	public abstract ApiSelectorBuilder addScanBasePackages(ApiSelectorBuilder builder);
}
