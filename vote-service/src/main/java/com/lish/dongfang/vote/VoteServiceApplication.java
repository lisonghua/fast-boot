package com.lish.dongfang.vote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.lish.dongfang.core.doc.FastSwaggerConfig;

/**
 * 配置自动扫描路径："com.lish.dongfang.core"框架核心类包；"com.lish.dongfang.vote.*"应用类包
 * @author lisong
 *
 */
@SpringBootApplication(scanBasePackages= {
		"com.lish.dongfang.core",
		"com.lish.dongfang.vote.service",
		"com.lish.dongfang.vote.ms"})
@EntityScan(basePackages= {"com.lish.dongfang.vote"})
@EnableJpaRepositories(basePackages= {"com.lish.dongfang.vote"})
@EnableEurekaClient
public class VoteServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoteServiceApplication.class, args);
	}

}
