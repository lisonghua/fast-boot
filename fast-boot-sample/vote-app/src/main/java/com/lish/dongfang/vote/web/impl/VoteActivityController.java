package com.lish.dongfang.vote.web.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lish.dongfang.core.FastBaseController;
import com.lish.dongfang.core.web.Result;
import com.lish.dongfang.core.web.ResultGenerator;
import com.lish.dongfang.vote.common.VoteActivityStatus;
import com.lish.dongfang.vote.model.VoteActivity;
import com.lish.dongfang.vote.service.VoteActivityService;
import com.lish.dongfang.vote.utils.DateUtils;
import com.lish.dongfang.vote.web.IVoteActivityController;

/**
 * 投票活动rest服务接口类
 * @author lisong
 *
 */
@RestController
public class VoteActivityController extends FastBaseController implements IVoteActivityController {
	
	@Autowired
	private VoteActivityService activityService;
	
	/* (non-Javadoc)
	 * @see com.lish.dongfang.vote.web.IVoteActivityController#getOne(long)
	 */
	@Override
	public Result<VoteActivity> getOne(@PathVariable long id){
		return ResultGenerator.ok(activityService.getOneById(id));
	}
	
	/* (non-Javadoc)
	 * @see com.lish.dongfang.vote.web.IVoteActivityController#getAll(com.lish.dongfang.vote.model.VoteActivity, org.springframework.data.domain.Pageable)
	 */
	@Override
	public Result<Page<VoteActivity>> getAll(VoteActivity activity, Pageable pageable){
		Page<VoteActivity> page = activityService.getPageBySpeci(new Specification<VoteActivity>() {
			
			@Override
			public Predicate toPredicate(Root<VoteActivity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				if(!StringUtils.isEmpty(activity.getName())) {
					if(!DateUtils.isValidShortDate(activity.getName())) {
						list.add(cb.like(root.get("name").as(String.class), "%" + activity.getName() + "%"));
					}else {
						//是日期类型则按照开始时间和结束时间查询
						Date searchDate = DateUtils.formatShortDate(activity.getName());
						list.add(cb.lessThanOrEqualTo(root.get("startDate").as(Date.class), searchDate));
						list.add(cb.greaterThanOrEqualTo(root.get("endDate").as(Date.class), searchDate));
					}
				}
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		}, pageable);
		return ResultGenerator.ok(page);
	}
	
	/* (non-Javadoc)
	 * @see com.lish.dongfang.vote.web.IVoteActivityController#add(com.lish.dongfang.vote.model.VoteActivity)
	 */
	@Override
	public Result<VoteActivity> add(@RequestBody VoteActivity activity){
		activity.setStatus((byte)VoteActivityStatus.calculateStatus(activity.getStartDate(), activity.getEndDate()));
		return ResultGenerator.ok(activityService.create(activity));
	}
	
	/* (non-Javadoc)
	 * @see com.lish.dongfang.vote.web.IVoteActivityController#update(long, com.lish.dongfang.vote.model.VoteActivity)
	 */
	@Override
	public Result<VoteActivity> update(@PathVariable("id") long id,@RequestBody VoteActivity newEntity){
		VoteActivity old = activityService.getOneById(id);
		old.setName(newEntity.getName());
		old.setStartDate(newEntity.getStartDate());
		old.setEndDate(newEntity.getEndDate());
		old.setStatus((byte)VoteActivityStatus.calculateStatus(newEntity.getStartDate(), newEntity.getEndDate()));
		old.setRemark(newEntity.getRemark());
		return ResultGenerator.ok(activityService.update(old));
	}
	
	/* (non-Javadoc)
	 * @see com.lish.dongfang.vote.web.IVoteActivityController#delete(java.util.List)
	 */
	@Override
	public Result<String> delete(@RequestBody List<Long> ids){
		ids.forEach(id->{
			VoteActivity old = activityService.getOneById(id);
			old.setDeleteFlag((byte)0);
			activityService.update(old);
		});
        return ResultGenerator.ok();
	}
	
	/* (non-Javadoc)
	 * @see com.lish.dongfang.vote.web.IVoteActivityController#updateStatus(long, com.lish.dongfang.vote.model.VoteActivity)
	 */
	@Override
	public Result<VoteActivity> updateStatus(@PathVariable("id") long id,@RequestBody VoteActivity act){
		VoteActivity old = activityService.getOneById(id);
		if(act.getStatus()==VoteActivityStatus.CLOSED.getStatus()) {
			old.setStatus(act.getStatus());
		}else {
			old.setStatus((byte)VoteActivityStatus.calculateStatus(old.getStartDate(),old.getEndDate()));
		}
		activityService.update(old);
		return ResultGenerator.ok(activityService.update(old));
	}
}
