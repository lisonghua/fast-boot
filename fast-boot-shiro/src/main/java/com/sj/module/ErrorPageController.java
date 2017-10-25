package com.sj.module;

import com.sj.exception.SanJiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by sunxyz on 2017/3/13.
 */
@Controller
public class ErrorPageController {

    private static final Logger logger = LoggerFactory.getLogger(ErrorPageController.class);

    @GetMapping("/403")
    public void forbidden() {
        logger.error("------权限不足-------");
        throw new SanJiException("403", "权限不足");
    }

    @GetMapping("/404")
    public void error() {
        logger.error("------頁面不存在-------");
        throw new SanJiException("404", "頁面不存在");
    }
}
