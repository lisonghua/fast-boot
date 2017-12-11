package com.lish.dongfang.vote.web.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.lish.dongfang.core.FastBaseController;
import com.lish.dongfang.core.web.Result;
import com.lish.dongfang.core.web.ResultGenerator;
import com.lish.dongfang.vote.model.VoteActivity;
import com.lish.dongfang.vote.model.VoteCandidate;
import com.lish.dongfang.vote.service.CandidateService;
import com.lish.dongfang.vote.service.VoteActivityService;
import com.lish.dongfang.vote.web.ICandidateController;

@RestController
public class CandidateController extends FastBaseController implements ICandidateController {
	
	@Autowired
	private CandidateService candidateService;
	
	@Autowired
	private VoteActivityService activityService;
	
	@Override
	public Result<Page<VoteCandidate>> getCandidatesOfActivity(@PathVariable("actId") long actId, VoteCandidate candidate, Pageable pageable) {
		Page<VoteCandidate> page = candidateService.getPageBySpeci(new Specification<VoteCandidate>() {

			@Override
			public Predicate toPredicate(Root<VoteCandidate> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				list.add(cb.equal(root.get("voteActivity").get("id"), actId));
				if(!StringUtils.isBlank(candidate.getName())) {
					list.add(cb.equal(root.get("name"), candidate.getName()));
				}
				if(candidate.getBirthday()!=null) {
					list.add(cb.equal(root.get("birthday"), candidate.getBirthday()));
				}
				if(new Byte(candidate.getSex())!=null) {
					list.add(cb.equal(root.get("sex"), candidate.getSex()));
				}
				if(new Byte(candidate.getStatus())!=null) {
					list.add(cb.equal(root.get("status"), candidate.getStatus()));
				}
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		},pageable);
		return ResultGenerator.ok(page);
	}

	@Override
	public Result<VoteCandidate> getOne(long actId,long id) {
		return ResultGenerator.ok(candidateService.getOneById(id));
	}

	@Override
	public Result<VoteCandidate> add(long actId,VoteCandidate candidate) {
		VoteActivity activity = activityService.getOneById(actId);
		candidate.setVoteActivity(activity);
		return ResultGenerator.ok(candidateService.create(candidate));
	}

	@Override
	public Result<VoteCandidate> update(long id, VoteCandidate newEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<String> delete(List<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}
}
