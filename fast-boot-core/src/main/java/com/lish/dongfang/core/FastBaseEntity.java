package com.lish.dongfang.core;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class FastBaseEntity<T> extends BaseEntity<T> {
	
	@Column(name="create_user")
	private Long createUser;
	
	@Column(name="update_user")
	private Long updateUser;
	
	@Column(name="delete_flag")
	private byte deleteFlag;

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

	public byte getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(byte deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}
