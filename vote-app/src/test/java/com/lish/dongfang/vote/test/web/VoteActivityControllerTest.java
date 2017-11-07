package com.lish.dongfang.vote.test.web;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.alibaba.fastjson.JSONObject;
import com.lish.dongfang.core.web.Result;
import com.lish.dongfang.vote.model.VoteActivity;
import com.lish.dongfang.vote.test.BaseVoteTest;
import com.lish.dongfang.vote.web.VoteActivityController;

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
		Map<String,String> params = new HashMap<String,String>();
		params.put("name", "测试活动");
		params.put("sort", "name");
		params.put("size", "15");
		params.put("page", "1");
		Result<?> resultObj = sendGetReq4Rest("/api/vote/activity",params);
		assertEquals("SUCCESS", resultObj.getStatus().name());
	}
	
	@Test
	public void testAddRestApi() throws Exception {
		VoteActivity activity = new VoteActivity();
		activity.setName("测试活动");
		activity.setStartDate(new Date());
		activity.setEndDate(new Date());
		activity.setStatus(new Byte("1"));
		
		Result<?> resultObj = sendPostReq4Rest("/api/vote/activity",activity);
		assertEquals("SUCCESS", resultObj.getStatus().name());
		
		Map<String,String> params = new HashMap<String,String>();
		params.put("name", "测试活动");
		params.put("startDate", "2017-09-08");
		params.put("endDate", "2018-09-08");
		params.put("status", "1");
		
		resultObj = sendPostReq4Rest("/api/vote/activity",params);
		assertEquals("SUCCESS", resultObj.getStatus().name());
	}
	
	@Test
	public void testUpdateRestApi() throws Exception {
		//准备数据
		VoteActivity activity = new VoteActivity();
		activity.setName("测试活动");
		activity.setStartDate(new Date());
		activity.setEndDate(new Date());
		activity.setStatus(new Byte("1"));
		Result<VoteActivity> result = activityController.add(activity);
		long id = result.getContent().getId();
		//测试修改
		activity.setName("修改测试活动");
		activity.setStatus(new Byte("0"));
		Result<?> resultObj = sendPutReq4Rest("/api/vote/activity/"+id,activity);
		assertEquals("SUCCESS", resultObj.getStatus().name());
		JSONObject updatedActivity = (JSONObject)resultObj.getContent();
		assertEquals("修改测试活动",updatedActivity.getString("name"));
	}
	
	@Test
	public void testDeleteRestApi() throws Exception {
		//准备数据
		VoteActivity activity = new VoteActivity();
		activity.setName("测试活动");
		activity.setStartDate(new Date());
		activity.setEndDate(new Date());
		activity.setStatus(new Byte("1"));
		Result<VoteActivity> result = activityController.add(activity);
		long id = result.getContent().getId();
		//测试删除
		List<Long> ids = new ArrayList<Long>();
		ids.add(id);
		Result<?> resultObj = sendDeleteReq4Rest("/api/vote/activity/",ids);
		assertEquals("SUCCESS", resultObj.getStatus().name());
		resultObj = sendGetReq4Rest("/api/vote/activity/"+id);
		assertEquals(0,((JSONObject)resultObj.getContent()).get("deleteFlag"));
	}
	
	@Test
	public void testUpdateStatusRestApi() throws Exception {
		//准备数据
		VoteActivity activity = new VoteActivity();
		activity.setName("测试活动");
		activity.setStartDate(new Date());
		activity.setEndDate(new Date());
		activity.setStatus(new Byte("1"));
		Result<VoteActivity> result = activityController.add(activity);
		long id = result.getContent().getId();
		//修改状态
		activity.setStatus((byte)2);
		Result<?> resultObj = sendPutReq4Rest("/api/vote/activity/"+id+"/status/","{\"deleteFlag\":1,\"new\":true,\"status\":99}");
		assertEquals("SUCCESS", resultObj.getStatus().name());
	}
	
	public static void main(String[] args) {
		VoteActivity activity = new VoteActivity();
		
		System.out.println(JSONObject.toJSONString(activity));
	}
}
