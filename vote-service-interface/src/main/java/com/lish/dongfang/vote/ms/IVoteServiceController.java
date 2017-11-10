package com.lish.dongfang.vote.ms;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lish.dongfang.core.web.Result;
import com.lish.dongfang.vote.model.VoteActivity;

/**
 * 投票微服务接口类
 * @author lisong
 *
 */
@RequestMapping(value="/api/vote")
public interface IVoteServiceController {
	/**
	 * @param id
	 * @return
	 */
	@GetMapping("activity/{id}")
	public Result<VoteActivity> getActById(@PathVariable("id") long id);
}
