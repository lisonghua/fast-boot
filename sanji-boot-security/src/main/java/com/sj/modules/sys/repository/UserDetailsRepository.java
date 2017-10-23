package com.sj.modules.sys.repository;

import com.sj.modules.sys.domain.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by yangrd on 2017/7/15.
 */
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long>, JpaSpecificationExecutor<UserDetails> {

}
