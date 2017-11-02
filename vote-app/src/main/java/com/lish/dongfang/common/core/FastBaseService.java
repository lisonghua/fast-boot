package com.lish.dongfang.common.core;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

/**
 * service的基类
 * @author lisong
 *
 * @param <T> 模块对应repository
 * @param <E> 模块对应实体
 * @param <P> 实体的主键
 */
public abstract class FastBaseService<T extends FastBaseRepository<E, P>, E extends FastBaseEntity<E>, P extends Serializable> {
	
	@Autowired
	protected T repository;
	
	/**
	 * 根据主键获得实体
	 * @param pk
	 * @return
	 */
	@Transactional(readOnly=true)
	public E getOneById(P pk) {
		return repository.findOne(pk);
	}
	
	/**
	 * 获得所有记录
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<E> getAll(){
		return repository.findAll();
	}
	
	/**
	 * 根据Specification查询条件和翻页信息获得当前页数据
	 * @param spec
	 * @param pageable
	 * @return
	 */
	@Transactional(readOnly=true)
	public Page<E> getPageBySpeci(Specification<E> spec,Pageable pageable){
		return repository.findAll(spec, pageable);
	}
	
	/**
	 * 根据Example查询条件和翻页信息获得当前页数据
	 * @param example
	 * @param pageable
	 * @return
	 */
	@Transactional(readOnly=true)
	public Page<E> getPageByExamp(Example<E> example,Pageable pageable){
		return repository.findAll(example, pageable);
	}
	
	@Transactional
	public E create(E entity) {
		Date now = new Date();
		entity.setCreatedTime(now);
		entity.setModifiedTime(now);
		return repository.save(entity);
	}
	
	@Transactional
	public E update(E entity) {
		Date now = new Date();
		entity.setModifiedTime(now);
		return repository.saveAndFlush(entity);
	}
	
	@Transactional
	public void delete(E entity) {
		repository.delete(entity);
	}
	
	@Transactional
	public void delete(P pk) {
		repository.delete(pk);
	}
	
	@Transactional
	public void deleteInBatch(List<P> pks) {
		pks.forEach((id)->repository.delete(id));
	}
}
