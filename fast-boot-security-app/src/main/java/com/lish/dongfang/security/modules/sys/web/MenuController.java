package com.lish.dongfang.security.modules.sys.web;

import static com.lish.dongfang.core.web.ResultGenerator.error;
import static com.lish.dongfang.core.web.ResultGenerator.ok;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lish.dongfang.core.web.Result;
import com.lish.dongfang.security.modules.sys.domain.Menu;
import com.lish.dongfang.security.modules.sys.repository.MenuRepository;
import com.lish.dongfang.security.modules.sys.repository.RoleRepository;
import com.lish.dongfang.security.modules.sys.service.MenuTreeService;
import com.lish.dongfang.security.modules.sys.view.MenuTreeVO;

/**
 * Created by lish on 2017/7/18.
 */
@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired
    private MenuRepository repository;

    @Autowired
    private MenuTreeService menuTreeService;

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping
    public Result<Menu> add(@RequestBody Menu menu) {
        Long pid = menu.getParent().getId();
        Menu parent = repository.findOne(pid);
        if (Objects.isNull(parent)) {
            return error("pid 不存在");
        }
        Date now = new Date();
        menu.setModifiedTime(now);
        menu.setCreatedTime(now);
        menu.setParent(parent);
        return ok(repository.save(menu));
    }

    @Transactional
    @DeleteMapping
    public Result<String> delete(@RequestBody Long[] ids) {
        List<Long> idList = Arrays.asList(ids);
        boolean isDelete = !idList.stream().anyMatch(id -> roleRepository.countByMenuSet_id(id) > 0);
        if (isDelete) {
            isDelete = !idList.stream().anyMatch(id -> repository.countByParent_Id(id) > 0);
            if (isDelete) {
                idList.forEach(repository::delete);
                return ok();
            } else {
                return error("先删除子資源");
            }
        } else {
            return error("有权限正在使用该资源");
        }
    }

    @Transactional
    @PutMapping("{id}")
    public Result<Menu> update(@PathVariable("id") Menu old, @RequestBody Menu self) {
        if (Objects.isNull(self)) {
            return error("id 不存在");
        }
        updateVal(old, self);
        return ok(repository.save(old));
    }

    @GetMapping
    public Result<Collection<MenuTreeVO>> listSystem() {
        return ok(menuTreeService.listSystem());
    }

    @GetMapping("/{id}")
    public Result<Menu> get(@PathVariable("id") Menu menu) {
        return ok(menu);
    }

    @GetMapping("{pid}/child")
    public Result<List<Menu>> findByParent(@PathVariable("pid") Menu parent) {
        if (Objects.isNull(parent)) {
            return error("pid 不存在");
        }
        return ok(repository.findByParentOrderBySortAsc(parent));
    }

    private void updateVal(Menu old, Menu self) {
        Date now = new Date();
        old.setModifiedTime(now);
        old.setName(val(old::getName, self::getName));
        old.setIcon(val(old::getIcon, self::getIcon));
        old.setParent(val(old::getParent, self::getParent));
        old.setSort(val(old::getSort, self::getSort));
        old.setDepth(val(old::getDepth, self::getDepth));
        old.setSkin(val(old::getSkin, self::getSkin));
        old.setPermission(val(old::getPermission, self::getPermission));
    }

    private <T> T val(Supplier<T> oldVal, Supplier<T> newVal) {
        T oldV = oldVal.get();
        T newV = newVal.get();
        return Objects.nonNull(newV) ? newV : oldV;
    }
}
