package com.lish.dongfang.security.modules.sys.repository;

import org.springframework.data.repository.CrudRepository;

import com.lish.dongfang.security.modules.sys.domain.User;

/**
 * 用來查詢和用戶角色等相关信息
 * Created by lish on 2017/7/14.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    User findByLoginName(String loginName);

    Long countByRoleSet_Id(Long roleId);
}
