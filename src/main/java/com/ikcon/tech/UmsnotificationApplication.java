package com.ikcon.tech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@EnableEurekaClient
public class UmsnotificationApplication {

	public static void main(String[] args) {
		log.info("main() entered");
		SpringApplication.run(UmsnotificationApplication.class, args);
	}
    
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		log.info("Rest template bean created");
		return new RestTemplate();
	}
}
