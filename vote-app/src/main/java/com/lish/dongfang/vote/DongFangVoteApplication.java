package com.lish.dongfang.vote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.lish.dongfang.DongFangApplication;
import com.sj.SanjiBootSecurityApplication;

@SpringBootApplication(scanBasePackageClasses= {SanjiBootSecurityApplication.class,DongFangVoteApplication.class})
@ServletComponentScan(basePackageClasses= {SanjiBootSecurityApplication.class})
@EntityScan(basePackageClasses= {SanjiBootSecurityApplication.class,DongFangVoteApplication.class})
@EnableJpaRepositories(basePackageClasses= {SanjiBootSecurityApplication.class,DongFangApplication.class,DongFangVoteApplication.class})
public class DongFangVoteApplication {
	public static void main(String[] args) {
		SpringApplication.run(DongFangVoteApplication.class, args);
	}
}
