package com.sj.module.sys.service;

import com.sj.common.utils.EncryptionUtils;
import com.sj.module.sys.domain.User;
import com.sj.module.sys.repository.UserRepository;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by sunxyz on 2017/3/15.
 */
@Service
@CacheConfig(cacheNames = "users")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Cacheable(key = "'demoInfo_'+#loginName")
    public User findByLoginName(String loginName) {
        System.out.print("没有走缓存！" + loginName);
        return userRepository.findByLoginName(loginName);
    }

    public User save(User user) {
        user.setPassword(EncryptionUtils.getSha512Hash(user.getPassword()));
        return userRepository.save(user);
    }

}
