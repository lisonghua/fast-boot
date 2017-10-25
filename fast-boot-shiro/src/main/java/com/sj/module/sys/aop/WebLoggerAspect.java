package com.sj.module.sys.aop;

import com.alibaba.fastjson.JSON;
import com.sj.module.sys.domain.SiteLogger;
import com.sj.module.sys.publisher.SiteLoggerEventPublisher;
import com.sj.module.sys.repository.SiteLoggerRepository;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by yangrd on 2017/3/27.
 */
@Component
@Aspect
public class WebLoggerAspect {

    private static final Logger logger = LoggerFactory.getLogger(WebLoggerAspect.class);

    @Autowired
    private SiteLoggerEventPublisher siteLoggerEventPublisher;

    @Pointcut("execution(* com.sj.module.*.web.api.*.*(..))")
    public void webLog() {
    }

    /**
     * 记录后台权限接口操作的相关记录。
     * @see SiteLogger
     */ 
    @Before("@annotation(requiresPermissions)&&webLog()")
    public void doBeforeTask(JoinPoint joinPoint, RequiresPermissions requiresPermissions) {
        //获取shiro登录用户
        Subject subject = SecurityUtils.getSubject();
        PrincipalCollection principals = subject.getPrincipals();
        String currentLoginName = (String) principals.getPrimaryPrincipal();

        //获取请求参数
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();

        String remoteAddr = request.getRemoteAddr();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        String userAgent = request.getHeader("User-Agent");//用户浏览器

        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        String permissions = Arrays.toString(requiresPermissions.value());

        SiteLogger siteLogger = new SiteLogger(currentLoginName, remoteAddr, uri, queryString, method, userAgent, classMethod, permissions, new Date());
        siteLoggerEventPublisher.publish(siteLogger);
    }


}
