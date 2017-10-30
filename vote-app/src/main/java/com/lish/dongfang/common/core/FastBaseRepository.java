package com.lish.dongfang.common.core;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FastBaseRepository<T extends FastBaseEntity<T>, P extends Serializable>
		extends JpaRepository<T, P>, JpaSpecificationExecutor<T> {

}
