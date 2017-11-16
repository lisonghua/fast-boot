package com.lish.dongfang.cloud.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import de.codecentric.boot.admin.config.EnableAdminServer;

/**
 * 监控服务
 * @author lisong
 *
 */
@SpringBootApplication
@EnableEurekaClient
@EnableAdminServer//开启springBootAdmin监控
public class FastMonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastMonitorApplication.class, args);
	}

}
