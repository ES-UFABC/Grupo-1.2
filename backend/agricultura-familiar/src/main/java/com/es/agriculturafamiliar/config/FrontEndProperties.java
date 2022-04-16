package com.es.agriculturafamiliar.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Configuration
public class FrontEndProperties {
	
	@Value("${front-end.endpoints.email-confirmation}")
	private String emailConfirmationEndpoint;

}
