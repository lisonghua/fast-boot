package com.lish.dongfang.cloud.gateway.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lish.dongfang.cloud.gateway.db.entity.BlacklistEntity;
import com.lish.dongfang.cloud.gateway.db.entity.ZuulRouteEntity;

/**
 * DB工具类
 * @author lisong
 *
 */
@Service
public class DBHelper {
	@Autowired
    JdbcTemplate jdbcTemplate;
	
	/**
	 * 获得所有有效的路由
	 * @return
	 */
	public List<ZuulRouteEntity> getAllRoutes(){
		return jdbcTemplate.query("select * from gateway_api_define where enabled = true ",new BeanPropertyRowMapper<ZuulRouteEntity>(ZuulRouteEntity.class));
	}
	
	/**
	 * 获得配置的黑名单
	 * @return
	 */
	public List<BlacklistEntity> getBacklist(){
		return jdbcTemplate.query("select * from gateway_blacklist where enabled = true ",new BeanPropertyRowMapper<BlacklistEntity>(BlacklistEntity.class));
	}
}
