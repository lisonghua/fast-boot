package com.lish.dongfang.vote.test;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = { "com.lish.dongfang.vote.web", "com.lish.dongfang.vote.service" })
@EntityScan(basePackages = { "com.lish.dongfang.vote.model" })
@EnableJpaRepositories(basePackages = { "com.lish.dongfang.vote.repository" })
public class DongFangVoteTestApplication {

}
