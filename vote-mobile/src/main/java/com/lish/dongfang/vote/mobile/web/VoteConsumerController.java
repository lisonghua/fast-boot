package com.lish.dongfang.vote.mobile.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.lish.dongfang.core.FastBaseController;
import com.lish.dongfang.core.web.Result;
import com.lish.dongfang.vote.mobile.client.VoteRemoteServiceClient;
import com.lish.dongfang.vote.model.VoteActivity;

/**
 * vote服务的消费者
 * @author lisong
 *
 */
@RestController
@RequestMapping("/api/vote/mb")
public class VoteConsumerController extends FastBaseController {
	
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private VoteRemoteServiceClient voteClient;
	
	@GetMapping("activity/v1/{id}")
	public String getActivityByIdV1(@PathVariable long id) {
		logger.info("服务消费者调用开始，VoteConsumerController->getActivityById");
		return restTemplate.getForEntity("http://VOTE-SERVICE/api/vote/activity/"+id, String.class).getBody();
	}
	
	@GetMapping("activity/v2/{id}")
	public Result<VoteActivity> getActivityByIdV2(@PathVariable long id){
		return voteClient.getActById(id);
	}
}
