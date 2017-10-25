package com.sj.module.sys.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * //前台菜单 最好做缓存处理
 * Created by sunxyz on 2017/3/14.
 */
@JsonIgnoreProperties({"new", "level", "parent"})
@Entity
@Table(name = "sys_menu")
public class Menu extends BaseEntity<Long> {

    @ManyToOne
    private Menu parent;

    private String name;//名称

    private String description;//描述

    private String url;//地址

    private String icon;//图标

    private Long sort;//排序

    private Long level;//层级=父级+1

    private String permission; // 权限标识，例如views，del等等

    private Boolean visible;//用户是否可见->true可见，false不可见

    @Transient
    private Long parentId;

    {
        sort = 0L;
        parentId = 0L;
    }

    public Menu getParent() {
        return parent;
    }

    public Menu setParent(Menu parent) {
        this.parent = parent;
        return this;
    }

    public String getName() {
        return name;
    }

    public Menu setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Menu setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Menu setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getIcon() {
        return icon;
    }

    public Menu setIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public Long getSort() {
        return sort;
    }

    public Menu setSort(Long sort) {
        this.sort = sort;
        return this;
    }

    public Long getLevel() {
        return level;
    }

    public Menu setLevel(Long level) {
        this.level = level;
        return this;
    }

    public String getPermission() {
        return permission;
    }

    public Menu setPermission(String permission) {
        this.permission = permission;
        return this;
    }

    public Boolean getVisible() {
        return visible;
    }

    public Menu setVisible(Boolean visible) {
        this.visible = visible;
        return this;
    }

    public Long getParentId() {
        if (null != parent) {
            this.parentId = parent.getId();
        }
        return parentId;
    }
}
