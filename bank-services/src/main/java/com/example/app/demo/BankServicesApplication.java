package com.example.app.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.example"})
@EnableJpaRepositories("com.example")
@EntityScan("com.example")
public class BankServicesApplication {

	public static void main(String[] args){
		SpringApplication.run(BankServicesApplication.class, args);
      
       
	}

}
