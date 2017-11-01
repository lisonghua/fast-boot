package com.lish.dongfang.vote.service;

import org.springframework.stereotype.Service;

import com.lish.dongfang.common.core.FastBaseService;
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
