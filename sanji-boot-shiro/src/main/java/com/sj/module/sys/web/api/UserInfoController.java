package com.sj.module.sys.web.api;

import com.sj.common.Result;
import com.sj.module.sys.constant.RequestConstant;
import com.sj.module.sys.domain.User;
import com.sj.module.sys.repository.UserRepository;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sunxyz on 2017/3/18.
 */
@RestController
@RequestMapping(RequestConstant.USER_INFO_API)
public class UserInfoController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public Result<User> getCurrentUserInfo() {
        User userInfo = userRepository.findByLoginName(getCurrentLoginName());
        return Result.ok(userInfo);
    }

    private String getCurrentLoginName() {
        Subject subject = SecurityUtils.getSubject();
        PrincipalCollection principals = subject.getPrincipals();
        String currentLoginName = (String) principals.getPrimaryPrincipal();
        return currentLoginName;
    }

}
