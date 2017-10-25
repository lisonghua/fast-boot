package com.sj.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by yangrd on 2017/7/3.
 */
@Configuration
public class MVCConfig extends WebMvcConfigurerAdapter {

    //快速解决页面转向问题
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("/page/login.html");
        registry.addViewController("/").setViewName("/page/index.html");
    }
}
