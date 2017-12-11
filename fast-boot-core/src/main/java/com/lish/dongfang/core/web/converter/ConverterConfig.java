package com.lish.dongfang.core.web.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * web应用converter配置
 * @author lisong
 *
 */
@Configuration
public class ConverterConfig {
	
	private static Logger logger = LoggerFactory.getLogger(ConverterConfig.class);
	
	@Bean
	public DateConvert dateConverter() {
		logger.info("注册Web应用全局request参数转换日期类型工具");
		return new DateConvert();
	}
	
	@Bean
	public ByteConverter byteConverter() {
		logger.info("注册Web应用全局request参数转换Byte类型工具");
		return new ByteConverter();
	}
}
