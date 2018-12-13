package com.wd7.client1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@EnableEurekaClient
@SpringBootApplication
public class Client1Application {

	@Value("${server.port}")
	private String port;

	@RequestMapping("/hi")
	public String hello(HttpServletRequest request, HttpServletResponse response)
	{
		String res=request.getSession().getId();
		return "port: "+port+"<br>sessionid: "+res;
	}





	public static void main(String[] args) {
		SpringApplication.run(Client1Application.class, args);
	}
}
