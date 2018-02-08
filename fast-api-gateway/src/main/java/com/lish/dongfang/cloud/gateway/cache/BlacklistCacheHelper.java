package com.lish.dongfang.cloud.gateway.cache;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lish.dongfang.cloud.gateway.db.entity.BlacklistEntity;

/**
 * 黑名单缓存工具类
 * @author lisong
 *
 */
@Service
public class BlacklistCacheHelper {
	
	@Autowired
	private BlacklistCache cache;
	
	/**
	 * 获得黑名单
	 * @return
	 */
	public List<BlacklistEntity> getBlacklist(){
		return cache.getBlacklist();
	}
}
