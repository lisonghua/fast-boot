package com.lish.dongfang.security.modules.sys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lish.dongfang.security.modules.sys.domain.Menu;

/**
 * Created by lhw on 2017/7/14.
 */
public interface MenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findByParentOrderBySortAsc(Menu parent);

    Long countByParent_Id(Long pid);

}
