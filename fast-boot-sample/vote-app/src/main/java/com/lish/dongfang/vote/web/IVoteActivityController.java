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


@RequestMapping(value="/api/vote/activity")
public interface IVoteActivityController {
	
	@GetMapping("{id}")
	Result<VoteActivity> getOne(@PathVariable long id);
	
	@GetMapping
	Result<Page<VoteActivity>> getAll(VoteActivity activity, Pageable pageable);
	
	@PostMapping
	Result<VoteActivity> add(@RequestBody VoteActivity activity);
	
	@PutMapping("{id}")
	Result<VoteActivity> update(@PathVariable("id") long id, VoteActivity newEntity);
	
	@DeleteMapping
	Result<String> delete(@RequestBody List<Long> ids);

	/**
	 * 更新活动状态
	 * @param id
	 * @param newEntity
	 * @return
	 */
	@PutMapping("{id}/status")
	Result<VoteActivity> updateStatus(@PathVariable("id") long id, VoteActivity act);

}