package com.sj.config.security.handler;

import com.alibaba.fastjson.JSON;
import com.sj.common.ResultGenerator;
import com.sj.config.security.handler.utils.RequestUtils;
import com.sj.config.security.handler.utils.ResponseUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by yangrd on 2017/7/4.
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
