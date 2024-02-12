package com.incometracker.incomesourceservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class IncomeSourceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(IncomeSourceServiceApplication.class, args);
	}

}
