package com.es.agriculturafamiliar.service.validator.authentication.confirmation;

import org.springframework.stereotype.Component;

import com.es.agriculturafamiliar.entity.User;
import com.es.agriculturafamiliar.exception.AuthException;

@Component
public interface IAccountConfirmationValidator {
	
	void validate(User user, String token) throws AuthException;
}
