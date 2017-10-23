package com.sj.module;

import com.sj.common.Result;
import com.sj.exception.SanJiException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sunxyz on 2017/3/13.
 */
@RestControllerAdvice
public class GlobalDefaultExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    private static final Result<String> RESULT = Result.error("操作异常");

    private static final Result<String> UNAUTHORIZED = Result.error("未授权");

    @ExceptionHandler(value = Exception.class)
    public Result<String> defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
            throw e;
        if (e instanceof SanJiException) {
            return Result.error(e.getMessage());
        } else if (e instanceof UnauthorizedException) {
            return UNAUTHORIZED;
        } else {
            logger.error("Request path : [url:{}, method:{}] 发生异常 => [异常类：{}, 异常信息:{}]", req.getRequestURI(), req.getMethod(), e.getClass(), e.getMessage());
            return RESULT;
        }
    }
}
