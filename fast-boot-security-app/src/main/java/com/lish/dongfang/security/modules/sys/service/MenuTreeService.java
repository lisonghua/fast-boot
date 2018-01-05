package com.lish.dongfang.security.modules.sys.service;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.lish.dongfang.common.utils.mapper.BeanMapper;
import com.lish.dongfang.security.modules.sys.domain.Menu;
import com.lish.dongfang.security.modules.sys.domain.User;
import com.lish.dongfang.security.modules.sys.repository.MenuRepository;
import com.lish.dongfang.security.modules.sys.repository.UserRepository;
import com.lish.dongfang.security.modules.sys.view.MenuTreeVO;

/**
 * Created by lish on 2017/8/3.
 */
@Service
public class MenuTreeService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private UserRepository userRepository;

    public Collection<MenuTreeVO> listSystem() {
        return getMenuTree().getChildren();
    }

    //需要用户登陆成功才能使用
    public Collection<MenuTreeVO> listCurrentUserSystem() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByLoginName(userDetails.getUsername());
        Set<Menu> menuSet = user.getRoleSet().stream().flatMap(role -> role.getMenuSet().stream()).collect(Collectors.toSet());
        return getMenuTree(menuSet).getChildren();
    }

    public MenuTreeVO getMenuTree() {
        return getMenuTree(menuRepository.findAll());
    }

    private MenuTreeVO getMenuTree(Collection<Menu> menuCollection) {
        Map<Long, MenuTreeVO> menuTreeMap = menuCollection.stream().collect(Collectors.toMap(Menu::getId, o -> BeanMapper.map(o, MenuTreeVO.class)));
        menuTreeMap.forEach((k, v) -> {
            Long parentId = v.getParentId();
            if (parentId != -1) {
                menuTreeMap.get(parentId).addChildren(v);
            }
        });
        MenuTreeVO root = menuTreeMap.get(1L);
        return root;
    }

}
