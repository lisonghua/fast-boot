package com.sj.module.sys.service;

import com.sj.module.sys.domain.Menu;
import com.sj.module.sys.domain.Role;
import com.sj.module.sys.domain.User;
import com.sj.module.sys.view.MenuTreeVO;
import com.sj.module.sys.repository.MenuRepository;
import com.sj.module.sys.repository.UserRepository;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by sunxyz on 2017/3/15.
 */
@Service
public class MenuService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MenuRepository repository;

    /**
     * 层级添加+排序添加
     * Created by yangrd on 2017/4/29 16:04.
     */
    @Transactional
    public Menu save(Menu menu) {
        Menu parent = menu.getParent();
        //层级添加+排序添加
        menu.setLevel(parent.getLevel() + 1);
        menu.setSort(null != menu.getSort() ? menu.getSort() : 0L);
        menu.setVisible(null != menu.getVisible() ? menu.getVisible() : false);
        Date now = new Date();
        menu.setCreateTime(now);
        menu.setUpdateTime(now);
        if (menu.getSort() == 0L) {
            Menu brothers = repository.findFirstByParentOrderBySortDesc(parent);
            if (Objects.nonNull(brothers)) {
                menu.setSort(brothers.getSort() + 1);
            } else {
                menu.setSort(parent.getSort() * 10 + 1);
            }
        }
        return repository.save(menu);
    }

    /**
     * TODO 此处需要加入缓冲
     * Created by sunxyz on 2017/3/15.
     */
    public Set<MenuTreeVO> getMenuTreeByCurrentUser() {
        Set<MenuTreeVO> treeSet = new TreeSet<>((obj1, obj2) -> obj1.getSort().compareTo(obj2.getSort()));
        Map<Long, Set<Menu>> menuLevel = getMenuLevelByCurrentUser();//获取用户菜单
        Map<Long, MenuTreeVO> menuTreeVOMap = new HashMap<>();
        for (Map.Entry<Long, Set<Menu>> entry : menuLevel.entrySet()) {
            Set<Menu> menuSet = entry.getValue();
            if (treeSet.isEmpty()) {//顶级菜单
                menuSet.forEach(menu -> {
                    if (null == menu.getParent()) {//如果不存在父节点也就意味着是顶级菜单
                        MenuTreeVO menuTreeVO = new MenuTreeVO(menu.getId(), menu.getName(), menu.getIcon(), menu.getUrl(), menu.getDescription(), null == menu.getSort() ? 0 : menu.getSort());
                        treeSet.add(menuTreeVO);
                        menuTreeVOMap.put(menu.getId(), menuTreeVO);
                    }
                });
            } else {//子菜单
                menuSet.forEach(menu -> {
                    MenuTreeVO menuTreeVO = new MenuTreeVO(menu.getId(), menu.getName(), menu.getIcon(), menu.getUrl(), menu.getDescription(), null == menu.getSort() ? 0 : menu.getSort());
                    menuTreeVOMap.put(menu.getId(), menuTreeVO);
                    MenuTreeVO parentMenuTree = menuTreeVOMap.get(menu.getParent().getId());
                    if (parentMenuTree != null) {//对用户可见
                        parentMenuTree.getMenuTrees().add(menuTreeVO);
                    }
                });
            }
        }
        return treeSet;
    }

    /**
     * Created by sunxyz on 2017/4/29.
     */
    private Map<Long, Set<Menu>> getMenuLevelByCurrentUser() {
        User userInfo = userRepository.findByLoginName(getCurrentLoginName());
        Set<Role> roleSet = userInfo.getRoleSet();
        Map<Long, Set<Menu>> menuLevel = new TreeMap<>();
        if (null != roleSet) {
            roleSet.forEach(role -> {
                Set<Menu> menuSet = role.getMenuSet();
                if (null != menuSet) {
                    menuSet.forEach(menu -> {
                        Long level = menu.getLevel();
                        Set<Menu> menuLevelSet = menuLevel.get(level);
                        if (menuLevelSet == null) {
                            menuLevelSet = new HashSet<>();
                            menuLevel.put(level, menuLevelSet);
                        }
                        if (null != menu.getVisible()) {
                            if (menu.getVisible()) {//对用户是否可见
                                menuLevelSet.add(menu);
                            }
                        }
                    });
                }
            });
        }
        return menuLevel;
    }

    private String getCurrentLoginName() {
        Subject subject = SecurityUtils.getSubject();
        PrincipalCollection principals = subject.getPrincipals();
        String currentLoginName = (String) principals.getPrimaryPrincipal();
        return currentLoginName;
    }
}
