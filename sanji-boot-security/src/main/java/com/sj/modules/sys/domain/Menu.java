package com.sj.modules.sys.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sj.common.base.domain.BaseEntity;

import javax.persistence.*;
import java.util.List;

/**
 * //前台菜单 最好做缓存处理
 * Created by sunxyz on 2017/3/14.
 */
@JsonIgnoreProperties({"new"})
@Entity
@Table(name = "sys_menu")
public class Menu extends BaseEntity<Menu> {

    private String name;

    private String icon;

    private String description;

    private String url;

    private Long sort;

    @ManyToOne
    private Menu parent;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    @Enumerated
    private Type depth;//菜单类别对应的深度

    private String permission;//许可用于认证标识

    private String skin;//皮肤 查看skins.css对应的样式

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    public Type getDepth() {
        return depth;
    }

    public Menu setDepth(Type depth) {
        this.depth = depth;
        return this;
    }

    public String getPermission() {
        return permission;
    }

    public Menu setPermission(String permission) {
        this.permission = permission;
        return this;
    }

    public String getSkin() {
        return skin;
    }

    public Menu setSkin(String skin) {
        this.skin = skin;
        return this;
    }

    public enum Type {
        ROOT, SYSTEM, DIRECTORY, MENU
    }

}
