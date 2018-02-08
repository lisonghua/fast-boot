package com.lish.dongfang.cloud.gateway.filter;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lish.dongfang.cloud.gateway.cache.BlacklistCacheHelper;
import com.lish.dongfang.cloud.gateway.db.entity.BlacklistEntity;
import com.netflix.zuul.context.RequestContext;

/**
 * api网关处理请求时执行的过滤器
 * @author lisong
 *
 */
public class AccessControlFilter extends PreFilter {
	
	private static Logger logger = LoggerFactory.getLogger(AccessControlFilter.class);
	public static final String URL_SEPERATOR=";";
	private static final int FORBIDDEN_ACCESS_ERROR_CODE=401;
	
	@Autowired
	private BlacklistCacheHelper blacklistCacheHelper;
	
	public AccessControlFilter() {
		super();
	}

	@Override
	public Object run() {
		logger.info("----------执行pre过滤器逻辑-----------");
		RequestContext ctx= RequestContext.getCurrentContext();
        HttpServletRequest req=ctx.getRequest();
        String accessUrl = req.getRequestURL().toString();
        String ipAddr=this.getIpAddr(req);
        logger.info("请求IP地址为：[{0}]",ipAddr);
        if(!CheckIsAccess(accessUrl, ipAddr)) {
			ctx.setResponseStatusCode(FORBIDDEN_ACCESS_ERROR_CODE);
		    ctx.setSendZuulResponse(false);
		    ctx.setResponseBody("IpAddr is forbidden to access the urls!");
		}
        return null;
	}

	/**
	 * 判断当前地址是否可以访问
	 */
	private boolean CheckIsAccess(String accessUrl,String ipAddr) {
		//获取本地IP黑名单
        List<BlacklistEntity> backlist = blacklistCacheHelper.getBlacklist();
        for (BlacklistEntity backlistEntity : backlist) {
			if(isMatchIP(ipAddr,backlistEntity.getIpPattern())) {
				logger.info("当前IP【{0}】在黑名单中！！！",ipAddr);
				String forbiddenAll = backlistEntity.getForbiddenAll();
				String[] permitUrls = backlistEntity.getPermitUrlList();
				if("1".equals(forbiddenAll)) {//禁止访问所有url
					logger.info("当前IP【{0}】地址校验不通过，禁止访问所有url！！！",ipAddr);
				    return false;
				}else {
					for (String pattern : permitUrls) {//只允许访问部分url
						if(isMatchUrl(accessUrl,pattern)) {
							logger.info("当前IP【{0}】地址校验通过，允许访问当前地址：{1}！！！",ipAddr,accessUrl);
				            return true;
						}
					}
					logger.info("当前IP【{0}】地址校验不通过，禁止访问当前地址：{1}！！！",ipAddr,accessUrl);
					return false;
				}
			}
		}
        logger.info("当前IP【{0}】不在黑名单中，允许访问当前地址：{1}！！！",ipAddr,accessUrl);
        return true;
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
