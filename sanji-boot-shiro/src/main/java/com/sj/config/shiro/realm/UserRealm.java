package com.sj.config.shiro.realm;

import com.sj.module.sys.domain.Menu;
import com.sj.module.sys.domain.Role;
import com.sj.module.sys.domain.User;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by sunxyz on 2017/3/13.
 */
@Component
public class UserRealm extends AbstractUserRealm {

    @Override
    public UserRolesAndPermissions doGetRoleAuthorizationInfo(User userInfo) {
        Set<String> userRoles = new HashSet<>();
        Set<String> userPermissions = new HashSet<>();
        //获取当前用户下所有ACL权限列表
        //获取当前用户下拥有的所有角色列表
        Set<Role> roles = userInfo.getRoleSet();
        loadRolesAndPermissions(userRoles, userPermissions, roles);
        return new UserRolesAndPermissions(userRoles, userPermissions);
    }

    private void loadRolesAndPermissions(Set<String> userRoles, Set<String> userPermissions, Set<Role> roles) {
        if (null != roles) {
            roles.forEach(role -> {
                if (null != role.getRoleType()) {
                    if (!role.getRoleType().trim().equals("")) {
                        userRoles.add(role.getRoleType());
                    }
                }
                Set<Menu> menuSet = role.getMenuSet();
                loadPermissions(userPermissions, menuSet);
            });
        }
    }

    private void loadPermissions(Set<String> userPermissions, Set<Menu> menuSet) {
        if (null != menuSet) {
            Set<String> permissionResourcesNameSet = menuSet.stream().
                    filter(menu -> null != menu.getPermission() && !(menu.getPermission().trim().equals(""))).
                    filter(menu -> null != menu.getVisible()).
                    filter(Menu::getVisible).
                    map(Menu::getPermission).
                    collect(Collectors.toSet());
            userPermissions.addAll(permissionResourcesNameSet);
        }
    }
}