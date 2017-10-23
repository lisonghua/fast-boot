package com.sj.module.sys.web.api;

import com.sj.common.Result;
import com.sj.module.sys.constant.RequestConstant;
import com.sj.module.sys.domain.Menu;
import com.sj.module.sys.view.MenuTreeVO;
import com.sj.module.sys.manager.CacheManager;
import com.sj.module.sys.repository.MenuRepository;
import com.sj.module.sys.service.MenuService;
import com.sj.module.sys.service.TreeTableService;
import com.sj.module.sys.web.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by sunxyz on 2017/3/14.
 */
@RestController
@RequestMapping(RequestConstant.MENU_API)
public class MenuController extends BaseController<MenuRepository, Menu, Long> {

    @Autowired
    private MenuService menuService;

    @Autowired
    private TreeTableService treeService;

    @RequiresPermissions("sys:menu:add")
    @Transactional
    @PostMapping
    public Result<String> add(@RequestParam(name = "pid", defaultValue = "1") Menu parent, Menu menu) {
        menuService.save(menu.setParent(parent));
        return Result.ok();
    }

    @RequiresPermissions("sys:menu:delete")
    @Transactional
    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id) {
        return super.delete(id);
    }

    @RequiresPermissions("sys:menu:update")
    @Transactional
    @PutMapping("/{id}")
    public Result<String> update(@PathVariable("id") Menu old, Menu menu) {
        if (!old.getPermission().equals(menu.getPermission())) {//更新菜单权限标识之后清空缓存
            CacheManager.clearAllUserLoginInfo();
        }
        Menu news = valueUpdate(old, menu);
        return super.update(news);
    }

    //给 treeTable 使用
    @RequiresPermissions("sys:menu:view")
    @GetMapping
    public List<Menu> getTreeTable() {
        return treeService.listFlatMenuTree();
    }

    //给 zTree
    @RequiresPermissions("sys:menu:view")
    @GetMapping("/all")
    public List<Menu> getAll() {
        return repository.findAll();
    }


    //--需要用户登录之后使用 树形菜单构建 需要根据用户调整
    @GetMapping("/tree")
    public Set<MenuTreeVO> getTreeByCurrentUser() {
        return menuService.getMenuTreeByCurrentUser();
    }

    @GetMapping("/home_url")
    public String getHomeUrl() {
        return repository.findFirstByParentIsNull().getUrl();
    }

    private Menu valueUpdate(Menu old, Menu menu) {
        old.setName(null != menu.getName() ? menu.getName() : old.getName());
        old.setDescription(null != menu.getDescription() ? menu.getDescription() : old.getDescription());
        old.setUrl(null != menu.getUrl() ? menu.getUrl() : old.getUrl());
        old.setIcon(null != menu.getIcon() ? menu.getIcon() : old.getIcon());
        old.setSort(null != menu.getSort() ? menu.getSort() : old.getSort());
        old.setPermission(null != menu.getPermission() ? menu.getPermission() : old.getPermission());
        old.setVisible(null != menu.getVisible() ? menu.getVisible() : false);
        return old;
    }

}
