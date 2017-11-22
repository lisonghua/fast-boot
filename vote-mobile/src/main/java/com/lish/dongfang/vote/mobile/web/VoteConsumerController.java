package com.lish.dongfang.vote.mobile.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.lish.dongfang.core.FastBaseController;
import com.lish.dongfang.core.web.Result;
import com.lish.dongfang.core.web.ResultGenerator;
import com.lish.dongfang.vote.mobile.client.VoteRemoteServiceClient;
import com.lish.dongfang.vote.model.VoteActivity;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * vote服务的消费者
 * @author lisong
 *
 */

@RestController
public class VoteConsumerController extends FastBaseController implements IVoteServiceConsumer {
	
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private VoteRemoteServiceClient voteClient;
	
	/* (non-Javadoc)
	 * @see com.lish.dongfang.vote.mobile.web.IVoteServiceConsumer#getActivityByIdV1(long)
	 */
	@Override
	
	public String getActivityByIdV1(@PathVariable long id) {
		logger.info("服务消费者调用开始，VoteConsumerController->getActivityById");
		return restTemplate.getForEntity("http://VOTE-SERVICE/api/vote/activity/"+id, String.class).getBody();
	}
	
	/* (non-Javadoc)
	 * @see com.lish.dongfang.vote.mobile.web.IVoteServiceConsumer#getActivityByIdV2(long)
	 */
	@Override
	public Result<VoteActivity> getActivityByIdV2(@PathVariable long id){
		return voteClient.getActById(id);
	}
	
	/* (non-Javadoc)
	 * @see com.lish.dongfang.vote.mobile.web.IVoteServiceConsumer#getActivityByIdV3(long)
	 * 需要hystrix和turbine监控的方法可以添加HystrixCommand注解，不能放到接口中否则无效
	 */
	@Override
	@HystrixCommand(fallbackMethod = "fallback")
	public Result<VoteActivity> getActivityByIdV3(@PathVariable long id){
		try {
			//模拟服务调用超时
			Thread.sleep(5000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return voteClient.getActById(id);
	}
	
	/**
	 * 断路器回调方法，服务降级使用，方法签名必须与getActivityByIdV3方法相同
	 * @param id
	 * @return
	 */
	private Result<VoteActivity> fallback(long id) {
		logger.info("------------服务调用超时--------------");
		VoteActivity act = new VoteActivity();
        return ResultGenerator.error(act);
    }
}
