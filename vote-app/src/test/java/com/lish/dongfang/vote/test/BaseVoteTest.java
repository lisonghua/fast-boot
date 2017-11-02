package com.lish.dongfang.vote.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.util.MultiValueMap;

import com.alibaba.fastjson.JSONObject;
import com.lish.dongfang.common.core.FastBaseEntity;
import com.sj.common.Result;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=DongFangVoteTestApplication.class)
@AutoConfigureMockMvc
public class BaseVoteTest {
	
	/**
	 * 模拟MVC对象
	 */
	@Autowired
    protected MockMvc mockMvc;
	
	/**
	 * 远端rest服务测试客户端
	 */
	@Autowired
	protected TestRestTemplate restTemplate;
  
    /**
     * 在测试开始前初始化工作    
     */
    @Before 
    public void setup() {    
    	
    }
    
    @Test
    public void loadContext() {
    	
    }
    
    @TestConfiguration
    static class Config {
        @Bean
        public TestRestTemplate testRestTemplate() {
            return new TestRestTemplate(new RestTemplateBuilder().build());
        }
    }
    
    
	/**
	 * 模拟rest调用测试get请求
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	protected Result<?> sendGetReq4Rest(String url,Map<String,String> params) throws Exception {
		MockHttpServletRequestBuilder builder = get(url)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.contentType(MediaType.APPLICATION_JSON_UTF8);
		if(params!=null) {
			//设置参数
			params.forEach((key,value)->{
				builder.param(key, value);
			});
		}
		return sendRequest(builder);
	}
	
	/**
	 * 模拟rest调用测试get请求
	 * @param url
	 * @return
	 * @throws Exception
	 */
	protected Result<?> sendGetReq4Rest(String url) throws Exception {
		return sendGetReq4Rest(url,null);
	}

	/**
	 * @param builder
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws Exception
	 */
	private String sendRequst(MockHttpServletRequestBuilder builder) throws UnsupportedEncodingException, Exception {
		String result = mockMvc.perform(builder)  
                .andExpect(status().isOk())// 模拟发送get请求    
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8    
                .andReturn().getResponse().getContentAsString();// 返回执行请求的结果
		return result;
	}
	
	/**
	 * 模拟rest调用测试post请求
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	protected Result<?> sendPostReq4Rest(String url,Map<String,String> params)throws Exception{
		MockHttpServletRequestBuilder builder = post(url)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.contentType(MediaType.APPLICATION_JSON_UTF8);
		builder.content(JSONObject.toJSONString(params));
		return sendRequest(builder);
	}
	
	/**
	 * 模拟rest调用测试post请求
	 * @param url
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	protected <T extends FastBaseEntity<?>> Result<?> sendPostReq4Rest(String url,T entity)throws Exception{
		MockHttpServletRequestBuilder builder = post(url)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.contentType(MediaType.APPLICATION_JSON_UTF8);
		builder.content(JSONObject.toJSONString(entity));
		return sendRequest(builder);
	}
	
	/**
	 * 模拟rest调用测试put请求
	 * @param url
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	protected <T extends FastBaseEntity<?>> Result<?> sendPutReq4Rest(String url,T entity)throws Exception{
		MockHttpServletRequestBuilder builder = put(url)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.contentType(MediaType.APPLICATION_JSON_UTF8);
		builder.content(JSONObject.toJSONString(entity));
		return sendRequest(builder);
	}
	
	/**
	 * 模拟rest调用测试delete请求
	 * @param url
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	protected <T extends FastBaseEntity<?>> Result<?> sendDeleteReq4Rest(String url,List<Long> ids)throws Exception{
		MockHttpServletRequestBuilder builder = delete(url)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.contentType(MediaType.APPLICATION_JSON_UTF8);
		builder.content(JSONObject.toJSONString(ids));
		return sendRequest(builder);
	}

	/**
	 * @param builder
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws Exception
	 */
	private Result<?> sendRequest(MockHttpServletRequestBuilder builder)
			throws UnsupportedEncodingException, Exception {
		String result = sendRequst(builder);
		Result<?> resultObj = JSONObject.parseObject(result, Result.class);
		return resultObj;
	}
}
