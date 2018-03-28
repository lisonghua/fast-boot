package com.lish.dongfang.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.lish.dongfang.auth.domain.UserDetails;

/**
 * Created by lish on 2017/7/15.
 */
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long>, JpaSpecificationExecutor<UserDetails> {

}
