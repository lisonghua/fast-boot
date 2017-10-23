package com.sj.modules.sys.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sj.common.base.domain.BaseEntity;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by sunxyz on 2017/3/13.
 */
@JsonIgnoreProperties({"news"})
@DynamicUpdate
@Entity
@Table(name = "sys_role")
public class Role extends BaseEntity<Role> {

    private String name;//给用户看

    private String description;//描述

    private String roleType;//权限类型，shiro将通过角色名来进行鉴权

    @ManyToMany
    @JoinTable(name = "sys_role_menu", joinColumns = {@JoinColumn(name = "role_id")}, inverseJoinColumns = {@JoinColumn(name = "menu_id")})
    private Set<Menu> menuSet;

    {
        roleType = "";
    }

    public String getName() {
        return name;
    }

    public Role setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Role setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getRoleType() {
        return roleType;
    }

    public Role setRoleType(String roleType) {
        this.roleType = roleType;
        return this;
    }

    public Set<Menu> getMenuSet() {
        return menuSet;
    }

    public Role setMenuSet(Set<Menu> menuSet) {
        this.menuSet = menuSet;
        return this;
    }
}
