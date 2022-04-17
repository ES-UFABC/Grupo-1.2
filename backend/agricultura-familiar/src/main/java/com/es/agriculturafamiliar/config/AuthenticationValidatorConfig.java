package com.es.agriculturafamiliar.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.es.agriculturafamiliar.entity.User;
import com.es.agriculturafamiliar.service.validator.authentication.CredentialsValidator;
import com.es.agriculturafamiliar.service.validator.authentication.IAuthenticationValidator;
import com.es.agriculturafamiliar.service.validator.authentication.UserEnabledValidator;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class AuthenticationValidatorConfig {
	
	private final PasswordEncoder passwordEncoder;	
	
	@Bean
	public List<IAuthenticationValidator<User, User>> authenticationValidators() {
		return List.of(new UserEnabledValidator(),
				new CredentialsValidator(passwordEncoder));
		
	}

}
