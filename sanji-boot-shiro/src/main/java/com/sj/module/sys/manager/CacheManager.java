package com.sj.module.sys.manager;

import com.sj.common.utils.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;

/**
 * Created by yangrd on 2017/4/29 16:00.
 */
public class CacheManager {

    private static final Logger log = LoggerFactory.getLogger(CacheManager.class);

    private static EhCacheCacheManager cacheCacheManager = SpringContextHolder.getBean(EhCacheCacheManager.class);

    /**
     * 更权限等关键信息之后 清空所有用户登录信息
     */
    public static void clearAllUserLoginInfo() {
        log.info("clearUserLoginInfo ");
        Cache cache = cacheCacheManager.getCache("authorizationCache");
        cache.clear();
    }

}
