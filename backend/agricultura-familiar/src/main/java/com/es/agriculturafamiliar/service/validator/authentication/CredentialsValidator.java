package com.es.agriculturafamiliar.service.validator.authentication;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.es.agriculturafamiliar.entity.User;
import com.es.agriculturafamiliar.exception.AuthException;
import com.es.agriculturafamiliar.exception.InvalidCredentialsException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class CredentialsValidator implements IAuthenticationValidator<User, User> {
	
    private final PasswordEncoder passwordEncoder;

	@Override
	public void validate(User user, User loadedUser) throws AuthException {        
        boolean isPasswordValid = passwordEncoder.matches(user.getPassword(), loadedUser.getPassword());
        
        if (!isPasswordValid) {
        	log.info("Credenciais fornecidas para o usuário são inválidas");
            throw new InvalidCredentialsException();
        }
        
        log.info("Credenciais fornecidas são válidas");		
	}
	

}
