package com.sj.module.sys.web.home;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by sunxyz on 2017/3/13.
 */
@Controller
public class HomeController {

    @RequiresAuthentication
    @GetMapping({"/index", "/", "/home"})
    public String index(Model model) {
        return "index";
    }

    @RequiresAuthentication
    @GetMapping({"/welcome"})
    public String welcome() {
        return "welcome";
    }
}
