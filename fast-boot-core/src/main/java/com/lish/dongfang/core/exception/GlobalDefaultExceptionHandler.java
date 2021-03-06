package com.lish.dongfang.core.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.lish.dongfang.core.web.Result;
import com.lish.dongfang.core.web.ResultGenerator;

@RestControllerAdvice
public class GlobalDefaultExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    @Value("${sanji.config.show-exception}")
    private Boolean showException = false;

    @ExceptionHandler(value = Exception.class)
    public Result<String> defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        if(showException){
            e.printStackTrace();
        }
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
            throw e;
        if (e instanceof SanJiException) {
            return ResultGenerator.error(e.getMessage());
        } else {
            LOGGER.error("Request path : [url:{}, method:{}] 发生异常 => [异常类：{}, 异常信息:{}]", req.getRequestURI(), req.getMethod(), e.getClass(), e.getMessage());
            return ResultGenerator.error("系统操作异常 异常信息:" + e.getMessage());
        }
    }
}
