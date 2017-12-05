package com.lish.dongfang.security.config.security.userdetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.lish.dongfang.security.modules.sys.domain.Role;
import com.lish.dongfang.security.modules.sys.domain.User;
import com.lish.dongfang.security.modules.sys.repository.UserRepository;

/**
 * Created by lish on 2017/7/3.
 */
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        User user = userRepository.findByLoginName(loginName);
        if (user == null) {
            throw new UsernameNotFoundException("not found");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        Set<Role> roles = user.getRoleSet();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleType()));
            logger.debug("loginName is {} role: {}", loginName, role.getRoleType());
        }
        return new org.springframework.security.core.userdetails.User(loginName, user.getPassword(), user.isEnabled(), true, true, true, authorities);
    }

}