package com.es.agriculturafamiliar.service.validator.authentication;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.es.agriculturafamiliar.exception.AuthException;

@Component
public interface IAuthenticationValidator<T extends UserDetails, U extends UserDetails> {
	void validate(T user, U loadedUser) throws AuthException;

}
