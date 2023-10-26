package com.ikcon.tech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UmsnotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(UmsnotificationApplication.class, args);
	}

}
