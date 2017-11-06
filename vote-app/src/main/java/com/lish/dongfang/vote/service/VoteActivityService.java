package com.lish.dongfang.vote.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lish.dongfang.core.FastBaseService;
import com.lish.dongfang.vote.model.VoteActivity;
import com.lish.dongfang.vote.repository.VoteActivityRepository;

/**
 * 投票活动服务
 * @author lisong
 *
 */
@Service
public class VoteActivityService extends FastBaseService<VoteActivityRepository,VoteActivity, Long> {
	
}
