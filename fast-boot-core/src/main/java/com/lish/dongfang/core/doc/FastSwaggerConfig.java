package com.lish.dongfang.core.doc;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * PropertySource来指定自定义的资源目录
 * encoding = "utf-8"属性防止中文乱码,不能为大写的"UTF-8"
 * @author lisong
 *
 */
@Configuration
@ConditionalOnProperty("fast.boot.swagger.enable")
@ConfigurationProperties(prefix="fast.boot.swagger")
@PropertySource(value="classpath:config/fast-swagger.yml",
	ignoreResourceNotFound=true,
	encoding="utf-8")
public class FastSwaggerConfig {
	private String title;

	private String description;

	private String serviceUrl;

	private String contact;

	private String version;

	private String license;

	private String licenseUrl;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getServiceUrl() {
		return serviceUrl;
	}
	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public String getLicenseUrl() {
		return licenseUrl;
	}
	public void setLicenseUrl(String licenseUrl) {
		this.licenseUrl = licenseUrl;
	}
}
