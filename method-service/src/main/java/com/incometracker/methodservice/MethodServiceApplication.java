package com.incometracker.methodservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MethodServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MethodServiceApplication.class, args);
	}

}
