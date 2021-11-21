package com.example.app.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example")
public class BankServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankServicesApplication.class, args);
	}

}
