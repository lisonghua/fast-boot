package com.sj.config.shiro.util;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by yangrd on 2017/4/28.
 */
public class ResponseUtil {

    public static void out(HttpServletResponse response, Object object) throws IOException {
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println(JSON.toJSONString(object));
        out.println();
        out.flush();
        out.close();
    }
}
