package com.lish.dongfang.vote.mobile.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lish.dongfang.core.web.Result;
import com.lish.dongfang.vote.model.VoteActivity;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 定义服务消费方接口
 * @author lisong
 *
 */
@Api(tags= {"vote微服务的消费方"})
@RequestMapping("/api/vote/mb")
public interface IVoteServiceConsumer {
	
	@ApiOperation(value="获取用户信息",notes="使用restTemplate调用其他微服务例子")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id",value="用户id",required=true,paramType="path",dataType="String")
	})
	@GetMapping("activity/v1/{id}")
	String getActivityByIdV1(@PathVariable long id);
	
	@ApiOperation(value="获取用户信息",notes="使用Feign客户端调用其他微服务例子")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id",value="用户id",required=true,paramType="path",dataType="String")
	})
	@GetMapping("activity/v2/{id}")
	Result<VoteActivity> getActivityByIdV2(@PathVariable long id);
	
	@ApiOperation(value="获取用户信息",notes="模拟调用其他微服务响应超时服务降级例子")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id",value="用户id",required=true,paramType="path",dataType="String")
	})
	@GetMapping("activity/v3/{id}")
	Result<VoteActivity> getActivityByIdV3(@PathVariable long id);

}