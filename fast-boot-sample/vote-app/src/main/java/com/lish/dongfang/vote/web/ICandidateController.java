package com.lish.dongfang.vote.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lish.dongfang.core.web.Result;
import com.lish.dongfang.vote.model.VoteCandidate;

@RequestMapping(value="/api/vote/candidate")
public interface ICandidateController {
	
	/**
	 * 获得活动的全部候选人
	 * @param actId
	 * @param candidate
	 * @param pageable
	 * @return
	 */
	@GetMapping("{actId}")
	Result<Page<VoteCandidate>> getCandidatesOfActivity(@PathVariable("actId") long actId,VoteCandidate candidate, Pageable pageable);
}