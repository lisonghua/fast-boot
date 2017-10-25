package com.sj.modules.sys.repository;

import com.sj.modules.sys.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * 用來查詢和用戶角色等相关信息
 * Created by yangrd on 2017/7/14.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    User findByLoginName(String loginName);

    Long countByRoleSet_Id(Long roleId);
}
