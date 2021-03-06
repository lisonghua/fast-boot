package com.lish.dongfang.auth.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lish.dongfang.auth.domain.Role;

/**
 * Created by lish on 2017/7/14.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    Page<Role> findByNameLike(String name, Pageable pageable);

    Long countByMenuSet_id(Long menuId);
}
