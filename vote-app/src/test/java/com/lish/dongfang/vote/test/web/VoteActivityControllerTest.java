package com.lish.dongfang.vote.test.web;


import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;

import com.alibaba.fastjson.JSONObject;
import com.lish.dongfang.vote.model.VoteActivity;
import com.lish.dongfang.vote.test.BaseVoteTest;
import com.lish.dongfang.vote.web.VoteActivityController;
import com.sj.common.Result;

public class VoteActivityControllerTest extends BaseVoteTest {
	@Autowired
	private VoteActivityController activityController;
	
	@Test
	public void testGetAll() {
		VoteActivity conditions = new VoteActivity();
		Pageable pageable = new PageRequest(1,5);
		Result<Page<VoteActivity>> result = activityController.getAll(conditions, pageable);
		assertEquals("SUCCESS", result.getStatus().name());
	}
	
	@Test
	public void testGetAllRestApi() throws Exception {
		String result = mockMvc.perform(get("/api/vote/activity")
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.param("name", "测试活动"))  
                .andExpect(status().isOk())// 模拟发送get请求    
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8    
                .andReturn().getResponse().getContentAsString();// 返回执行请求的结果

		Result<?> resultObj = JSONObject.parseObject(result, Result.class);
		assertEquals("SUCCESS", resultObj.getStatus().name());
	}
}
