package com.sj.module.sys.repository;

import com.sj.module.sys.domain.SiteLogger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by yangrd on 2017/4/5.
 */
public interface SiteLoggerRepository extends JpaRepository<SiteLogger, Long> {

    Page<SiteLogger> findByUserLoginNameLikeAndRequestMethodAndRequestTimeBetween(String name, String requestMethod, Date startDate, Date endDate, Pageable pageable);

    //-- excel导出使用

    List<SiteLogger> findByUserLoginNameLikeAndRequestMethodAndRequestTimeBetween(String name, String requestMethod, Date startDate, Date endDate);
}
