package com.lish.dongfang.vote.web;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lish.dongfang.core.web.Result;
import com.lish.dongfang.vote.model.VoteActivity;
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
	
	@GetMapping("{actId}/{id}")
	Result<VoteCandidate> getOne(@PathVariable("actId") long actId,@PathVariable("id") long id);
	
	@PostMapping("{actId}")
	Result<VoteCandidate> add(@PathVariable("actId") long actId,@RequestBody VoteCandidate candidate);
	
	@PutMapping("{id}")
	Result<VoteCandidate> update(@PathVariable("id") long id, VoteCandidate newEntity);
	
	@DeleteMapping
	Result<String> delete(@RequestBody List<Long> ids);
}