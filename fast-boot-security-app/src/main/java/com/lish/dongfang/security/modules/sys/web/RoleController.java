package com.lish.dongfang.security.modules.sys.web;

import static com.lish.dongfang.core.web.ResultGenerator.error;
import static com.lish.dongfang.core.web.ResultGenerator.ok;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lish.dongfang.core.web.Result;
import com.lish.dongfang.security.modules.sys.domain.Role;
import com.lish.dongfang.security.modules.sys.repository.RoleRepository;
import com.lish.dongfang.security.modules.sys.repository.UserRepository;

/**
 * Created by lish on 2017/7/17.
 */
@RestController
@RequestMapping(value = "/api/role", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RoleController {

    @Autowired
    private RoleRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @PostMapping
    public Result<String> add(@RequestBody Role role) {
        Date now = new Date();
        role.setCreatedTime(now);
        role.setModifiedTime(now);
        repository.save(role);
        return ok();
    }

    @Transactional
    @DeleteMapping
    public Result<String> delete(@RequestBody Long[] ids) {
        List<Long> idList = Arrays.asList(ids);
        boolean isDelete = !idList.stream().anyMatch(id -> userRepository.countByRoleSet_Id(id) > 0);
        if (isDelete) {
            idList.forEach(repository::delete);
            return ok();
        }else{
            return error("有用户在使用该权限");
        }
    }

    @Transactional
    @PutMapping("/{id}")
    public Result<String> update(@PathVariable("id") Role old, @RequestBody Role role) {
        if (Objects.isNull(old)) {
            return error();
        }
        Date now = new Date();
        old.setModifiedTime(now);
        old.setName(role.getName());
        old.setRoleType(role.getRoleType());
        old.setDescription(role.getDescription());
        old.setMenuSet(role.getMenuSet());
        return ok();
    }

    @GetMapping("/{id}")
    public Result<Role> get(@PathVariable("id") Role role) {
        return ok(role);
    }


    @GetMapping
    public Result<Page<Role>> getAll(@RequestParam(defaultValue = "") String name, Pageable pageable) {
        return ok(repository.findByNameLike("%" + name + "%", pageable));
    }


}
