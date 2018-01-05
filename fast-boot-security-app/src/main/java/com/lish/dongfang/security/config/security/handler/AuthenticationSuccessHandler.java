package com.lish.dongfang.security.config.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.alibaba.fastjson.JSON;
import com.lish.dongfang.core.web.ResultGenerator;
import com.lish.dongfang.security.config.security.handler.utils.RequestUtils;
import com.lish.dongfang.security.config.security.handler.utils.ResponseUtils;

/**
 * Created by lish on 2017/7/4.
 */
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private static final String LOGIN_SUCCESS_RESULT = JSON.toJSONString(ResultGenerator.ok("登录成功"));

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
        if (RequestUtils.isAjax(request)) {
            ResponseUtils.print(response, LOGIN_SUCCESS_RESULT);
        } else {
            super.onAuthenticationSuccess(request, response, auth);
        }
    }

}
