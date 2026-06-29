package com.matheus.ecommerce_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.matheus.ecommerce_api")
@EnableJpaRepositories(basePackages = "com.matheus.ecommerce_api.repository")
@EntityScan(basePackages = "com.matheus.ecommerce_api.model")
public class EcomerceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomerceApiApplication.class, args);
	}
}