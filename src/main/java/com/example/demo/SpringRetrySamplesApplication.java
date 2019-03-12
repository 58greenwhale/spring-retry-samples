package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableRetry
@EnableJpaRepositories(basePackages= {"com.example.demo"})
public class SpringRetrySamplesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRetrySamplesApplication.class, args);
	}

}
