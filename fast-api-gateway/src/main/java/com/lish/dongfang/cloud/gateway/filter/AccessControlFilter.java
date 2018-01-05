package com.lish.dongfang.cloud.gateway.filter;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lish.dongfang.cloud.gateway.db.DBHelper;
import com.lish.dongfang.cloud.gateway.db.entity.BacklistEntity;
import com.netflix.zuul.context.RequestContext;

/**
 * api网关处理请求时执行的过滤器
 * @author lisong
 *
 */
public class AccessControlFilter extends PreFilter {
	
	private static Logger logger = LoggerFactory.getLogger(AccessControlFilter.class);

	public AccessControlFilter(DBHelper dbHelper) {
		super(dbHelper);
	}
	
	public AccessControlFilter() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object run() {
		logger.info("----------执行pre过滤器逻辑-----------");
		RequestContext ctx= RequestContext.getCurrentContext();
        HttpServletRequest req=ctx.getRequest();
        String accessUrl = req.getRequestURL().toString();
        String ipAddr=this.getIpAddr(req);
        logger.info("请求IP地址为：[{}]",ipAddr);
        //获取本地IP黑名单
        List<BacklistEntity> backlist = dbHelper.getBacklist();
        for (BacklistEntity backlistEntity : backlist) {
			if(isMatchIP(ipAddr,backlistEntity.getIpPattern())) {
				String forbiddenAll = backlistEntity.getForbiddenAll();
				if("1".equals(forbiddenAll)) {//禁止访问所有url
					logger.info("IP地址校验不通过！！！");
		            ctx.setResponseStatusCode(401);
		            ctx.setSendZuulResponse(false);
		            ctx.setResponseBody("IpAddr is forbidden!");
				}else {
					List<String> permitUrls = backlistEntity.getPermitUrls();
					for (String pattern : permitUrls) {//只允许访问部分url
						if(isMatchUrl(accessUrl,pattern)) {
							logger.info("IP地址校验不通过！！！");
							ctx.setResponseStatusCode(401);
				            ctx.setSendZuulResponse(false);
				            ctx.setResponseBody("IpAddr is forbidden!");
						}
					}
				}
			}
		}
        logger.info("IP校验通过。");
        return null;
	}
	
	/**
	 * 判断ip是否匹配
	 * @param target
	 * @param pattern
	 * @return
	 */
	private boolean isMatchIP(String target,String pattern) {
		Pattern patt=Pattern.compile(pattern);
		Matcher matcher = patt.matcher(target);
		if(matcher.matches()) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断url是否匹配
	 * @param target
	 * @param pattern
	 * @return
	 */
	private boolean isMatchUrl(String target,String pattern) {
		Pattern patt=Pattern.compile(pattern);
		Matcher matcher = patt.matcher(target);
		if(matcher.matches()) {
			return true;
		}
		return false;
	}
	
	/* 
	 * 通过int值来定义过滤器的执行顺序，数值越小优先级越高
	 * (non-Javadoc)
	 * @see com.netflix.zuul.ZuulFilter#filterOrder()
	 */
	@Override
	public int filterOrder() {
		return 0;
	}
	
	/**
     * 获取Ip地址
     * @param request
     * @return
     */
    private String getIpAddr(HttpServletRequest request){

        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
