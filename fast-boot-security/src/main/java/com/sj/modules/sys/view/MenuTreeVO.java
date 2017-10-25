package com.sj.modules.sys.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sj.modules.sys.domain.Menu;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by yangrd on 2017/8/3.
 */
public class MenuTreeVO implements Comparable<MenuTreeVO> {

    private Long id;

    private String name;

    private String icon;

    private String description;

    private String url;

    private Long sort;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private Menu.Type depth;//菜单类别对应的深度

    private String permission;//许可用于认证标识

    private String skin;//皮肤 查看skins.css对应的样式

    private Set<MenuTreeVO> children;

    @JsonIgnore
    private MenuTreeVO parent;

    public Long getId() {
        return id;
    }

    public MenuTreeVO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public MenuTreeVO setName(String name) {
        this.name = name;
        return this;
    }

    public String getIcon() {
        return icon;
    }

    public MenuTreeVO setIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MenuTreeVO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public MenuTreeVO setUrl(String url) {
        this.url = url;
        return this;
    }

    public Long getSort() {
        return sort;
    }

    public MenuTreeVO setSort(Long sort) {
        this.sort = sort;
        return this;
    }

    public Menu.Type getDepth() {
        return depth;
    }

    public MenuTreeVO setDepth(Menu.Type depth) {
        this.depth = depth;
        return this;
    }

    public String getPermission() {
        return permission;
    }

    public MenuTreeVO setPermission(String permission) {
        this.permission = permission;
        return this;
    }

    public String getSkin() {
        return skin;
    }

    public MenuTreeVO setSkin(String skin) {
        this.skin = skin;
        return this;
    }

    public Set<MenuTreeVO> getChildren() {
        return children;
    }

    public MenuTreeVO setChildren(Set<MenuTreeVO> children) {
        this.children = children;
        return this;
    }

    public MenuTreeVO getParent() {
        return parent;
    }

    public MenuTreeVO setParent(MenuTreeVO parent) {
        this.parent = parent;
        return this;
    }

    public void addChildren(MenuTreeVO menuTreeVO) {
        if (Objects.isNull(this.children)) {
            synchronized (this) {
                if (Objects.isNull(this.children)) {
                    children = new TreeSet<>();
                }
            }
        }
        this.children.add(menuTreeVO);
    }

    public Long getParentId() {
        return Objects.nonNull(parent) ? parent.getId() : -1L;
    }

    @Override
    public int compareTo(MenuTreeVO o) {
        return (int) (Objects.nonNull(getSort()) ? getSort() - o.getSort() : getId() - o.getId());
    }
}
