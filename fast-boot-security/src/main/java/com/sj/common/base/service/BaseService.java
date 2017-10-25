package com.sj.common.base.service;

import com.sj.common.base.domain.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * Created by yangrd on 2017/7/1.
 */
public abstract class BaseService<D extends CrudRepository<T, Long>, T extends BaseEntity> {

    @Autowired
    protected D repository;

    @Transactional
    public T save(T t) {
        try {
            Date now = new Date();
            t.setCreatedTime(now);
            t.setModifiedTime(now);
            return repository.save(t);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw e;
        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            repository.delete(id);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw e;
        }
    }

    @Transactional
    public T update(T t) {
        try {
            Date now = new Date();
            t.setModifiedTime(now);
            return repository.save(t);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw e;
        }
    }

    public D $() {
        return repository;
    }

}