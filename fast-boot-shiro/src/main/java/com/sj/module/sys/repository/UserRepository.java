package com.sj.module.sys.repository;

import com.sj.module.sys.domain.Role;
import com.sj.module.sys.domain.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

/**
 * Created by sunxyz on 2017/3/13.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    @Cacheable(value = "users", key = "'demoInfo_'+#p0")
    User findByLoginName(String loginName);

    Page<User> findByRoleSetIdAndLoginNameLike(Long roleId,String loginName, Pageable pageable);

    Page<User> findByLoginNameLike(String loginName, Pageable pageable);;
}
