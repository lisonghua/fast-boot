package com.lish.dongfang.cloud.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class})
@EnableTurbine//开启turbine功能
public class FastTurbineApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastTurbineApplication.class, args);
	}

}
