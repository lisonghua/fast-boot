package com.lish.dongfang.cloud.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * hystrix控制面板应用程序
 * @author lisong
 *
 */
@SpringBootApplication
@EnableEurekaClient
@EnableHystrixDashboard//打开hystrix监控面板
public class FastHystrixDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastHystrixDashboardApplication.class, args);
	}

}
