package com.sj.modules.sys.repository;

import com.sj.modules.sys.domain.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by yangrd on 2017/7/14.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    Page<Role> findByNameLike(String name, Pageable pageable);

    Long countByMenuSet_id(Long menuId);
}
