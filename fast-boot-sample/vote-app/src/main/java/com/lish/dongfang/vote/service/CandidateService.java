package com.lish.dongfang.vote.service;

import org.springframework.stereotype.Service;

import com.lish.dongfang.core.FastBaseService;
import com.lish.dongfang.vote.model.VoteCandidate;
import com.lish.dongfang.vote.repository.CandidateRepository;

@Service
public class CandidateService extends FastBaseService<CandidateRepository, VoteCandidate, Long> {
	
}
