package com.lish.dongfang.vote.mobile.client;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.lish.dongfang.vote.ms.IVoteServiceController;

/**
 * 调用vote微服务的客户端
 * @author lisong
 *
 */
@FeignClient("VOTE-SERVICE")
public interface VoteRemoteServiceClient extends IVoteServiceController {
	
}
