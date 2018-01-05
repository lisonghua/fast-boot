package com.lish.dongfang.core.web.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.alibaba.fastjson.JSON;
import com.lish.dongfang.core.web.Result;

@RestControllerAdvice
public class LogResponseBodyAdvice implements ResponseBodyAdvice<Result<Object>> {
	
	private Logger logger = LoggerFactory.getLogger(LogResponseBodyAdvice.class);
	
	/**
	 * 是否可以打印日志
	 */
	@Value("${fast.boot.response.log.enable}")
	private boolean enable;

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	@Override
	public Result<Object> beforeBodyWrite(Result<Object> body, MethodParameter returnType,
			MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
			ServerHttpRequest request, ServerHttpResponse response) {
		if (enable) {
			logger.info("--------打印response body开始-------------");
			logger.info("Response Body：");
			logger.info(JSON.toJSONString(body));
			logger.info("--------打印response body结束-------------");
		}
		return body;
	}
}
