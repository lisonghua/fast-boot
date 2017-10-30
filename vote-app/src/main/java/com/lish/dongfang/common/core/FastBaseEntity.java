package com.lish.dongfang.common.core;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.sj.common.base.domain.BaseEntity;

@MappedSuperclass
public class FastBaseEntity<T> extends BaseEntity<T> {
	
	@Column(name="create_user")
	private Long createUser;
	
	@Column(name="update_user")
	private Long updateUser;

	public Long getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}

	public Long getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}
}
