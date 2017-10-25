package com.sj.module.sys.domain;

import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * 只针对具有权限标识的部分进行日志记录
 * 参考 类:
 *
 * @see com.sj.module.sys.web.api.MenuController
 * 日志记录：
 * @see com.sj.module.sys.aop.WebLoggerAspect;
 * 使用easyPoi 导出
 * Created by yangrd on 2017/4/5.
 */
@ExcelTarget("SiteLogger")
@Entity
@Table(name = "sys_logger")
public class SiteLogger extends BaseEntity<Long> {

    @Excel(name = "请求用户", orderNum = "5", needMerge = true)
    private String userLoginName;//用户的登录名

    private String remoteAddr; 	// 操作用户的IP地址

    @Excel(name = "类别", orderNum = "1", needMerge = true)
    private String requestUri;//请求路径

    @Excel(name = "请求参数", orderNum = "3", needMerge = true)
    private String requestParams;//请求参数

    @Excel(name = "请求方法", orderNum = "2", needMerge = true)
    private String requestMethod;//请求方法类型

    @Excel(name = "请求信息", orderNum = "7", needMerge = true)
    private String requestUserAgent;//浏览器类型

    private String classMethod;//请求的类+方法

    @Excel(name = "请求权限验证", orderNum = "4", needMerge = true)
    private String permissions;//请求权限验证

    @Excel(name = "请求日期", orderNum = "6", needMerge = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date requestTime;//请求日期


    public SiteLogger() {
    }

    public SiteLogger(String userLoginName, String remoteAddr, String requestUri, String requestParams, String requestMethod, String requestUserAgent, String classMethod, String permissions, Date requestTime) {
        this.userLoginName = userLoginName;
        this.remoteAddr = remoteAddr;
        this.requestUri = requestUri;
        this.requestParams = requestParams;
        this.requestMethod = requestMethod;
        this.requestUserAgent = requestUserAgent;
        this.classMethod = classMethod;
        this.permissions = permissions;
        this.requestTime = requestTime;
    }

    public String getUserLoginName() {
        return userLoginName;
    }

    public SiteLogger setUserLoginName(String userLoginName) {
        this.userLoginName = userLoginName;
        return this;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public SiteLogger setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
        return this;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public SiteLogger setRequestUri(String requestUri) {
        this.requestUri = requestUri;
        return this;
    }

    public String getRequestParams() {
        return requestParams;
    }

    public SiteLogger setRequestParams(String requestParams) {
        this.requestParams = requestParams;
        return this;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public SiteLogger setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
        return this;
    }

    public String getRequestUserAgent() {
        return requestUserAgent;
    }

    public SiteLogger setRequestUserAgent(String requestUserAgent) {
        this.requestUserAgent = requestUserAgent;
        return this;
    }

    public String getClassMethod() {
        return classMethod;
    }

    public SiteLogger setClassMethod(String classMethod) {
        this.classMethod = classMethod;
        return this;
    }

    public String getPermissions() {
        return permissions;
    }

    public SiteLogger setPermissions(String permissions) {
        this.permissions = permissions;
        return this;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public SiteLogger setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
        return this;
    }
}
