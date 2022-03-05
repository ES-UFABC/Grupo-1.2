package com.es.agriculturafamiliar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
public class AgriculturaFamiliarApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgriculturaFamiliarApplication.class, args);
	}

}
