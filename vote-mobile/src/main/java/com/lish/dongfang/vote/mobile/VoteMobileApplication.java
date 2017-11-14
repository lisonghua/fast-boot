package com.lish.dongfang.vote.mobile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * "com.lish.dongfang.cloud.core"必须扫描目录
 * @author lisong
 *
 */
@SpringBootApplication(
		scanBasePackages= {"com.lish.dongfang.cloud.core","com.lish.dongfang.vote.mobile"},
		exclude={DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class})
@EnableEurekaClient
@EnableFeignClients//打开feign客户端
@EnableHystrix//打开hystrix熔断机制
public class VoteMobileApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoteMobileApplication.class, args);
	}
}
