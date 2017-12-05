package com.lish.dongfang.vote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * "com.lish.dongfang.core"：核心包
 * "com.lish.dongfang.security"：安全相关包
 * "com.lish.dongfang.vote"：自身应用包
 * 
 * @author lisong
 *
 */
@SpringBootApplication(scanBasePackages= {"com.lish.dongfang.security","com.lish.dongfang.vote"})
@ServletComponentScan(basePackages= {"com.lish.dongfang.security"})
@EntityScan(basePackages= {"com.lish.dongfang.security","com.lish.dongfang.vote"})
@EnableJpaRepositories(basePackages= {
		"com.lish.dongfang.security",
		"com.lish.dongfang.core",
		"com.lish.dongfang.vote"})
public class DongFangVoteApplication {
	public static void main(String[] args) {
		SpringApplication.run(DongFangVoteApplication.class, args);
	}
}
