package com.sj.modules.sys.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sj.common.base.domain.BaseEntity;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by sunxyz on 2017/3/13.
 * 只提供登录功能
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "password", "news", "roleSet"})
@DynamicUpdate
@Entity
@Table(name = "sys_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseEntity<User> {

    @NotEmpty(message = "用户名不能为空")
    @Column(unique = true, updatable = false)
    private String loginName;

    @NotEmpty(message = "密码不能为空")
    @Column(nullable = false)
    private String password;

    @Enumerated
    private UserStatus status;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "sys_user_role", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roleSet;

    {
        status = UserStatus.NORMAL;
    }

    public String getLoginName() {
        return loginName;
    }

    public User setLoginName(String loginName) {
        this.loginName = loginName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserStatus getStatus() {
        return status;
    }

    public User setStatus(UserStatus status) {
        this.status = status;
        return this;
    }

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public User setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
        return this;
    }

    public boolean isEnabled() {
        return !this.status.equals(UserStatus.FROZEN);
    }

    public enum UserStatus {
        NORMAL,//正常
        ABNORMAL,//异常
        FROZEN//冻结
    }
}
