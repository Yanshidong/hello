package com.wd7.authcentercpn1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class AuthCenterCpN1Application {

	public static void main(String[] args) {
		SpringApplication.run(AuthCenterCpN1Application.class, args);
	}

}

