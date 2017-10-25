package com.sj.module.sys.web;

import com.sj.common.Result;
import com.sj.module.sys.domain.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by sunxyz on 2017/3/14.
 */
public class BaseController<D extends JpaRepository<T, PK>, T extends BaseEntity<PK>, PK extends Serializable> {

    @Autowired
    protected D repository;

    @Transactional
    protected Result<String> save(T t) {
        Date now = new Date();
        t.setCreateTime(now);
        return updateTime(t, now);
    }

    @Transactional
    protected Result<String> update(T t) {
        return updateTime(t, new Date());
    }

    @Transactional
    protected Result<String> delete(PK id) {
        try {
            repository.delete(id);
            return Result.ok();
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.error();
        }
    }

    private Result<String> updateTime(T t, Date now) {
        try {
            t.setUpdateTime(now);
            repository.save(t);
            return Result.ok();
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.error();
        }
    }

}
