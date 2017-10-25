package com.sj.module.sys.service;

import com.sj.module.sys.domain.Menu;
import com.sj.module.sys.repository.MenuRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by sunxyz on 2017/3/15.
 */
@Service
public class TreeTableService {

    private static final Logger logger = LoggerFactory.getLogger(TreeTableService.class);

    @Autowired
    private MenuRepository repository;

    private static final Comparator<Tree> TREE_COMPARATOR = (obj1, obj2) -> obj1.getSelf().getSort().compareTo(obj2.getSelf().getSort());// 升序排序

    ThreadLocal<Menu> menuThreadLocal = new ThreadLocal<>();

    /**
     * 对树进行展平
     * Created by sunxyz on 2017/3/15.
     */
    public List<Menu> listFlatMenuTree() {
        Tree rootTree = getTree();
        List<Menu> flatMenuList = new ArrayList<>();
        addFlatMenu(rootTree, flatMenuList);
        return flatMenuList;
    }

    private void addFlatMenu(Tree tree, List<Menu> flatMenuList) {
        flatMenuList.add(tree.getSelf());
        Set<Tree> treeSet = tree.getChild();
        if (Objects.nonNull(treeSet)) {
            treeSet.forEach(tree1 ->
                    addFlatMenu(tree1, flatMenuList)
            );
        }

    }

    private Tree getTree() {
        Map<Long, Set<Menu>> menuGroup = getMenuGroup();//获取所有的菜单
        Tree rootTree = new Tree(menuThreadLocal.get());
        buildTree(rootTree, menuGroup);
        return rootTree;
    }

    private void buildTree(Tree parent, Map<Long, Set<Menu>> menuGroup) {
        Long parentId = parent.getSelf().getId();
        Set<Menu> childMenu = menuGroup.get(parentId);
        if (Objects.nonNull(childMenu)) {
            Set<Tree> child = childMenu.stream().map(Tree::new).collect(Collectors.toCollection(() -> new TreeSet<>(TREE_COMPARATOR)));
            parent.setChild(child);
            child.forEach(c -> buildTree(c, menuGroup));
        }
    }

    //以parent_id作为key
    private Map<Long, Set<Menu>> getMenuGroup() {
        List<Menu> menuList = repository.findAll();
        menuThreadLocal.set(menuList.parallelStream().filter(menu -> menu.getId() == 1).findFirst().get());//设置root
        return menuList.stream().collect(Collectors.groupingBy(Menu::getParentId, Collectors.toSet()));
    }

    class Tree {
        private Menu self;

        private Set<Tree> child;

        public Tree(Menu self) {
            this.self = self;
        }

        public Menu getSelf() {
            return self;
        }

        public void setSelf(Menu self) {
            this.self = self;
        }

        public Set<Tree> getChild() {
            return child;
        }

        public void setChild(Set<Tree> child) {
            this.child = child;
        }
    }
}
