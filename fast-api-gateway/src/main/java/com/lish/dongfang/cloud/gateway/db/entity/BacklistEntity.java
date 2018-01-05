package com.lish.dongfang.cloud.gateway.db.entity;

import java.util.List;

/**
 * 黑名单实体
 * @author lisong
 *
 */
public class BacklistEntity {
	/**
	 * 客户端IP地址匹配模式
	 */
	private String ipPattern;
	/**
	 * 客户端主机名
	 */
	private String host;
	/**
	 * 允许访问的url地址或者正则表达式
	 */
	private List<String> permitUrls;
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
	public List<String> getPermitUrls() {
		return permitUrls;
	}
	public void setPermitUrls(List<String> permitUrls) {
		this.permitUrls = permitUrls;
	}
	public String getForbiddenAll() {
		return forbiddenAll;
	}
	public void setForbiddenAll(String forbiddenAll) {
		this.forbiddenAll = forbiddenAll;
	}
}
