package com.lish.dongfang.vote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import com.sj.SanjiBootSecurityApplication;

@SpringBootApplication
@ServletComponentScan
public class DongFangVoteApplication {
	public static void main(String[] args) {
		SpringApplication.run(SanjiBootSecurityApplication.class, args);
	}
}
