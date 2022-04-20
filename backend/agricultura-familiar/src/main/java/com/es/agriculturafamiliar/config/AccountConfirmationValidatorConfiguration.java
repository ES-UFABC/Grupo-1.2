package com.es.agriculturafamiliar.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;

import com.es.agriculturafamiliar.service.validator.authentication.confirmation.IAccountConfirmationValidator;
import com.es.agriculturafamiliar.service.validator.authentication.confirmation.MatchTokenValidator;
import com.es.agriculturafamiliar.service.validator.authentication.confirmation.TokenExpiryValidator;

@Configuration
public class AccountConfirmationValidatorConfiguration {
	
	public List<IAccountConfirmationValidator> accountConfirmationValidator() {
		return List.of(
				new MatchTokenValidator(),
				new TokenExpiryValidator()		
		);
	}

}
