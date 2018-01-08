package it.pkg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * "com.lish.dongfang.core"：核心包
 * "com.lish.dongfang.security"：安全相关包
 * "it.pkg"：自身应用包（根据自定义修改）
 * 
 * @author lisong
 *
 */
@SpringBootApplication(scanBasePackages= {
		"com.lish.dongfang.core",
		"com.lish.dongfang.security",
		"it.pkg"})
@ServletComponentScan(basePackages= {"com.lish.dongfang.core"})//加载数据源监控模块
@EntityScan(basePackages= {"com.lish.dongfang.security","it.pkg"})
@EnableJpaRepositories(basePackages= {
		"com.lish.dongfang.security",
		"com.lish.dongfang.core",
		"it.pkg"})
public class FaseDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(FaseDemoApplication.class, args);
	}
}
