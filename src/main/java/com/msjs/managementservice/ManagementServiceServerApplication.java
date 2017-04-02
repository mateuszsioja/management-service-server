package com.msjs.managementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "com.msjs.managementservice")
public class ManagementServiceServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagementServiceServerApplication.class, args);
	}
}
