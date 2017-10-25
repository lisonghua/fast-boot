package com.sj.config.shiro.realm;

import com.sj.module.sys.domain.User;
import com.sj.module.sys.repository.UserRepository;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by sunxyz on 2017/3/13.
 */
public abstract class AbstractUserRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(AbstractUserRealm.class);

    @Autowired
    private UserRepository userRepository;

    public abstract UserRolesAndPermissions doGetRoleAuthorizationInfo(User userInfo);

    public AbstractUserRealm() {
        this.setAuthorizationCacheName("authorizationCache");
        this.setAuthenticationCacheName("authenticationCache");
    }

    /**
     * 获取授权信息
     * Created by sunxyz on 2017/3/27.
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String currentLoginName = (String) principals.getPrimaryPrincipal();
        Set<String> userRoles = new HashSet<>();
        Set<String> userPermissions = new HashSet<>();
        //从数据库中获取当前登录用户的详细信息
        User userInfo = userRepository.findByLoginName(currentLoginName);
        if (Objects.nonNull(userInfo)) {
            UserRolesAndPermissions roleContainer = doGetRoleAuthorizationInfo(userInfo);
            if (Objects.nonNull(roleContainer)) {
                userRoles.addAll(roleContainer.getUserRoles());
                userPermissions.addAll(roleContainer.getUserPermissions());
            }

        } else {
            throw new AuthorizationException();
        }
        //为当前用户设置角色和权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRoles(userRoles);
        authorizationInfo.addStringPermissions(userPermissions);
        logger.info("###【获取角色成功】[SessionId] => {}", SecurityUtils.getSubject().getSession().getId());
        return authorizationInfo;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authenticationToken) throws AuthenticationException {
        //UsernamePasswordToken对象用来存放提交的登录信息
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //查出是否有此用户
        User user = userRepository.findByLoginName(token.getUsername());
        if (Objects.nonNull(user)) {
            // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
            return new SimpleAuthenticationInfo(user.getLoginName(), user.getPassword(), getName());
        }
        return null;
    }

    protected class UserRolesAndPermissions {
        Set<String> userRoles;
        Set<String> userPermissions;

        public UserRolesAndPermissions(Set<String> userRoles, Set<String> userPermissions) {
            this.userRoles = userRoles;
            this.userPermissions = userPermissions;
        }

        public Set<String> getUserRoles() {
            return userRoles;
        }

        public Set<String> getUserPermissions() {
            return userPermissions;
        }
    }
}
