package com.sj.config.shiro.filter;

import com.sj.common.Result;
import com.sj.config.shiro.util.RequestUtil;
import com.sj.config.shiro.util.ResponseUtil;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yangrd on 2017/4/28.
 * 扩展了 authc 并根据是否是 ajax请求返回不同的数据
 */
public class MyAuthenticationFilter extends FormAuthenticationFilter {

    private static final Logger log = LoggerFactory.getLogger(MyAuthenticationFilter.class);

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //判断是不是Ajax请求
        if (RequestUtil.isAjax((HttpServletRequest) request)) {
            //输出json串
            if (isLoginRequest(request, response)) {
                if (isLoginSubmission(request, response)) {
                    log.trace("Login submission detected.  Attempting to execute login.");
                    return executeLogin(request, response);
                } else {
                    log.trace("Login page view.");
                    //allow them to see the login page ;)
                    return true;
                }
            } else {
                log.trace("Attempting to access a path which requires authentication.  Forwarding to the " +
                        "Authentication url [" + getLoginUrl() + "]");
                ResponseUtil.out((HttpServletResponse) response, Result.error("登陆过时").setStatus(Result.Status.KICKOUT));
                return false;
            }

        } else {
            return super.onAccessDenied(request, response);
        }
    }


}
