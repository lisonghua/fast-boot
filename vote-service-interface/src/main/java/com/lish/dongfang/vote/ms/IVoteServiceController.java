package com.lish.dongfang.vote.ms;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lish.dongfang.core.web.Result;
import com.lish.dongfang.vote.model.VoteActivity;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

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
	@ApiOperation(value="获取用户信息",notes="根据用户id获取用户信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id",value="用户id",required=true,paramType="path",dataType="String")
	})
	@GetMapping("activity/{id}")
	public Result<VoteActivity> getActById(@PathVariable("id") long id);
}
