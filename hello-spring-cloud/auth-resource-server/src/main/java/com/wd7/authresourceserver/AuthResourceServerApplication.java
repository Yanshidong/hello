package com.wd7.authresourceserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class AuthResourceServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthResourceServerApplication.class, args);
	}

}

