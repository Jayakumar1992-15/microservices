package com.in28minutes.microservices.currency_conversion_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

// ...existing code...
@SpringBootApplication
@EnableFeignClients(basePackages = "com.in28minutes.microservices.currency_conversion_service")
public class CurrencyConversionServiceApplication {
    // ...existing code...
	public static void main(String[] args) {
		SpringApplication.run(CurrencyConversionServiceApplication.class, args);
	}

}
