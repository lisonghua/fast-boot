package com.sj.module.sys.repository;

import com.sj.module.sys.domain.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by sunxyz on 2017/3/13.
 */
public interface MenuRepository extends JpaRepository<Menu, Long> {

    Menu findFirstByParentOrderBySortDesc(Menu parent);

    Menu findFirstByParentIsNull();

}