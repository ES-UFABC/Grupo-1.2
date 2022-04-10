package com.es.agriculturafamiliar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableAsync
@EnableRetry
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class})
public class AgriculturaFamiliarApplication{
	public static void main(String[] args) {
		SpringApplication.run(AgriculturaFamiliarApplication.class, args);
	}
}
