package com.bh.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsAccountsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsAccountsServiceApplication.class, args);
	}

}
