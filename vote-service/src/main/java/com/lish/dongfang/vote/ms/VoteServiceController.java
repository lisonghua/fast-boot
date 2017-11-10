package com.lish.dongfang.vote.ms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.lish.dongfang.core.FastBaseController;
import com.lish.dongfang.core.web.Result;
import com.lish.dongfang.core.web.ResultGenerator;
import com.lish.dongfang.vote.model.VoteActivity;
import com.lish.dongfang.vote.service.VoteActivityService;

/**
 * 投票系统微服务
 * @author lisong
 *
 */
@RestController
public class VoteServiceController extends FastBaseController implements IVoteServiceController{
	
	@Autowired
	private VoteActivityService actService;
	
	@Override
	public Result<VoteActivity> getActById(@PathVariable("id") long id){
		logger.info("调用服务提供者：VoteServiceController->getActById");
		return ResultGenerator.ok(actService.getOneById(id));
	}
}
