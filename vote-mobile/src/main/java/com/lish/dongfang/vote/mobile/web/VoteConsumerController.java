package com.lish.dongfang.vote.mobile.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.lish.dongfang.core.FastBaseController;

@RestController
@RequestMapping("/api/vote/mb")
public class VoteConsumerController extends FastBaseController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("activity/{id}")
	public String getActivityById(@PathVariable long id) {
		logger.info("服务消费者调用开始，VoteConsumerController->getActivityById");
		return restTemplate.getForEntity("http://VOTE-SERVICE/api/vote/activity/"+id, String.class).getBody();
	}
}
