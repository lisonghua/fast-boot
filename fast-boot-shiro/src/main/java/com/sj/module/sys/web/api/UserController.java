package com.sj.module.sys.web.api;

import com.sj.common.Result;
import com.sj.common.utils.EncryptionUtils;
import com.sj.module.sys.constant.RequestConstant;
import com.sj.module.sys.domain.Role;
import com.sj.module.sys.domain.User;
import com.sj.module.sys.repository.RoleRepository;
import com.sj.module.sys.repository.UserRepository;
import com.sj.module.sys.service.UserService;
import com.sj.module.sys.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by yangrd on 2017/4/19.
 */
@RestController
@RequestMapping(RequestConstant.USER_API)
public class UserController extends BaseController<UserRepository, User, Long> {

    private static final String DEFAULT_PASSWORD = EncryptionUtils.getSha512Hash("123456");

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping
    public Result<String> reg(User user, @RequestParam(value = "roles[]", required = false) String[] roles) {
        if (user.getLoginName().trim().equals("")) {
            return Result.error("用户信息填写不完整");
        }
        Set<Long> roleIds = Arrays.stream(roles).map(Long::valueOf).collect(Collectors.toSet());
        Set<Role> roleSet = roleRepository.findAll(roleIds).stream().collect(Collectors.toSet());
        user.setNickname(user.getLoginName());
        user.setRoleSet(roleSet);
        user.setPassword(DEFAULT_PASSWORD);
        return super.save(user);
    }

    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable("id") Long userId) {
        return super.delete(userId);
    }

    @PutMapping("/{id}")
    public Result<String> update(@PathVariable("id") User user, User news, @RequestParam(value = "roles[]", required = false) String[] roles) {
        user.setPassword(EncryptionUtils.getSha512Hash(user.getPassword()));
        Set<Long> roleIds = Arrays.stream(roles).map(Long::valueOf).collect(Collectors.toSet());
        Set<Role> roleSet = roleRepository.findAll(roleIds).stream().collect(Collectors.toSet());
        user.setRoleSet(roleSet);
        return super.update(user);
    }

    @GetMapping
    public Page<User> findByLoginNameLike(@RequestParam(defaultValue = "") String name, Pageable pageable) {
        return repository.findByLoginNameLike("%" + name + "%", pageable);
    }
}
