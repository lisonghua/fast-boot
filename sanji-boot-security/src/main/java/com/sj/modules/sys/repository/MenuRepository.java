package com.sj.modules.sys.repository;

import com.sj.modules.sys.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by lhw on 2017/7/14.
 */
public interface MenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findByParentOrderBySortAsc(Menu parent);

    Long countByParent_Id(Long pid);

}
