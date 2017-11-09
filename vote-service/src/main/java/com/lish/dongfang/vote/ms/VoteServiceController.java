package com.lish.dongfang.vote.ms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping(value="/api/vote")
public class VoteServiceController extends FastBaseController {
	
	@Autowired
	private VoteActivityService actService;
	
	@GetMapping("activity/{id}")
	public Result<VoteActivity> getActById(@PathVariable long id){
		logger.info("调用服务提供者：VoteServiceController->getActById");
		return ResultGenerator.ok(actService.getOneById(id));
	}
}
