package com.lish.dongfang.cloud.gateway.db.entity;

import org.apache.commons.lang.StringUtils;

import com.lish.dongfang.cloud.gateway.filter.AccessControlFilter;

/**
 * 黑名单实体
 * @author lisong
 *
 */
public class BlacklistEntity {
	
	/**
	 * 客户端IP地址匹配模式
	 */
	private String ipPattern;
	/**
	 * 客户端主机名
	 */
	private String host;
	/**
	 * 允许访问的url地址或者正则表达式，多个url用;隔开
	 */
	private String permitUrls;
	
	private String[] permitUrlList;
	/**
	 * 禁止访问所有url，1：是；0：否，默认1
	 */
	private String forbiddenAll;
	public String getIpPattern() {
		return ipPattern;
	}
	public void setIpPattern(String ipPattern) {
		this.ipPattern = ipPattern;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPermitUrls() {
		return permitUrls;
	}
	public void setPermitUrls(String permitUrls) {
		this.permitUrls = permitUrls;
	}
	public String getForbiddenAll() {
		return forbiddenAll;
	}
	public void setForbiddenAll(String forbiddenAll) {
		this.forbiddenAll = forbiddenAll;
	}
	public String[] getPermitUrlList() {
		if(!StringUtils.isBlank(permitUrls)) {
			permitUrlList = permitUrls.split(AccessControlFilter.URL_SEPERATOR);
		}else {
			permitUrlList = new String[] {};
		}
		return permitUrlList;
	}
}
