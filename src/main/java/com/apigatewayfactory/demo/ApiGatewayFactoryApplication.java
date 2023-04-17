package com.apigatewayfactory.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;


@SpringBootApplication
public class ApiGatewayFactoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayFactoryApplication.class, args);
	}

}
