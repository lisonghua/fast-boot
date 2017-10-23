package com.sj.config.shiro.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yangrd on 2017/4/29.
 */
public final class RequestUtil {

    private RequestUtil() {
    }

    public static boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
    }
}
