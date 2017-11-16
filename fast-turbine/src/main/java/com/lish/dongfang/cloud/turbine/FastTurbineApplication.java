package com.lish.dongfang.cloud.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableTurbine//开启turbine功能
public class FastTurbineApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastTurbineApplication.class, args);
	}

}
