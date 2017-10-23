package com.sj.module.sys.web;

import com.sj.module.sys.domain.Role;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sunxyz on 2017/3/17.
 */
@Controller
@RequestMapping("/page/sys")
public class SysTemplateController {

    private static final String TEMPLATE_PATH = "/module/sys/";
    @GetMapping("/editPassword")
    public String editPassword() {
        return "user/editPassword";
    }

    @GetMapping("/user/edit")
    public String userEdit() {
        return "user/edit";
    }

    @GetMapping("/user/add")
    public String userAdd() {
        return "user/add";
    }

    @GetMapping("/user/index")
    public String userManage() {
        return "user/index";
    }
    @RequiresPermissions("sys:menu:edit")
    @GetMapping("/icons/frame")
    public String iconsFrame() {
        return template("icons");
    }

    @RequiresPermissions("sys:menu:view")
    @GetMapping("/menu")
    public String menu() {
        return template("menu");
    }

    @RequiresPermissions("sys:menu:edit")
    @GetMapping("/menu/frame")
    public String menuFrame() {
        return template("menu_frame");
    }

    @RequiresPermissions("sys:role:view")
    @GetMapping("/role")
    public String role() {
        return template("role");
    }

    @RequiresPermissions("sys:role:edit")
    @GetMapping("/role/frame")
    public String roleFrame() {
        return template("role_frame");
    }

    @RequiresPermissions("sys:role:view")
    @GetMapping("/role/{roleId}/user")
    public String roleUser(@PathVariable("roleId") Role role, Model model) {
        model.addAttribute("role", role);
        return template("role_user");
    }

    @RequiresPermissions("sys:log:view")
    @GetMapping("/log")
    public String log() {
        return template("log");
    }

    private String template(String template) {
        return TEMPLATE_PATH + template;
    }

}
