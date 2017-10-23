package com.sj.module.sys.view;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.*;

/**
 * Created by sunxyz on 2017/3/14.
 */
@JsonIgnoreProperties("sort")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MenuTreeVO {

    private Long id;//给前台选中使用

    private String name;

    private String icon;

    private String url;

    private String description;

    private Long sort;//排序

    private Set<MenuTreeVO> menuTrees;

    {
        menuTrees = new TreeSet<>((obj1, obj2) -> obj1.getSort().compareTo(obj2.getSort()));// 升序排序
    }

    public MenuTreeVO() {
    }

    public MenuTreeVO(Long id,String name, String icon, String url, String description, Long sort) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.url = url;
        this.description = description;
        this.sort = sort;
    }

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

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public Set<MenuTreeVO> getMenuTrees() {
        return menuTrees;
    }

    public void setMenuTrees(Set<MenuTreeVO> menuTrees) {
        this.menuTrees = menuTrees;
    }
}
