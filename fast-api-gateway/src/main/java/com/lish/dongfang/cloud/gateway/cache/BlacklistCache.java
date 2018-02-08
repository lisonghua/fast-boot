package com.lish.dongfang.cloud.gateway.cache;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lish.dongfang.cloud.gateway.db.DBHelper;
import com.lish.dongfang.cloud.gateway.db.entity.BlacklistEntity;

/**
 * 黑名单缓存
 * @author lisong
 *
 */
public class BlacklistCache {
	
	private static final List<BlacklistEntity> BLACKLIST_CACHE = new ArrayList<BlacklistEntity>();
	
	@Autowired
	private DBHelper dbHelper;
	
	/**
	 * 是否加载过缓存
	 */
	private Boolean loaded;
	
	public void refresh() {
		if(!BLACKLIST_CACHE.isEmpty()) {
			BLACKLIST_CACHE.clear();
		}
		List<BlacklistEntity> blacklistCache = dbHelper.getBacklist();
		BLACKLIST_CACHE.addAll(blacklistCache);
		loaded=true;
	}
	
	/**
	 * 获得缓存的黑名单
	 * @return
	 */
	protected List<BlacklistEntity> getBlacklist(){
		synchronized (loaded) {
			if (!loaded) {
				refresh();
				loaded=true;
			}
		}
		return BLACKLIST_CACHE;
	}
}
