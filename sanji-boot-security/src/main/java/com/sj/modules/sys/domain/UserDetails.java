package com.sj.modules.sys.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

/**
 * 添加用户详细信息 用户表尽量不要修改
 * Created by sunxyz on 2017/3/13.
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "news"})
@DynamicUpdate
@Entity
@Table(name = "sys_user_details")
@PrimaryKeyJoinColumn(name = "user_id")
public class UserDetails extends User {

    private String avatar;//头像

    private String nickname;//昵称

    private String description;//描述

    @Enumerated
    private Sex sex;//性别

    private Date birthday;//生日

    private String phone;//手机

    //    @Email
    private String email;//邮箱

    public String getAvatar() {
        return avatar;
    }

    public UserDetails setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public UserDetails setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UserDetails setDescription(String description) {
        this.description = description;
        return this;
    }

    public Sex getSex() {
        return sex;
    }

    public UserDetails setSex(Sex sex) {
        this.sex = sex;
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }

    public UserDetails setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserDetails setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDetails setEmail(String email) {
        this.email = email;
        return this;
    }

    public static enum Sex {
        MAN, WO_MAN
    }
}
