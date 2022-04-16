package com.es.agriculturafamiliar.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Getter
@Configuration
public class FrontEndProperties {
	
	@Value("${front-end.endpoints.email-confirmation}")
	private String emailConfirmationEndpoint;

}
