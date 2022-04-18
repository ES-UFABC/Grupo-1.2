package com.es.agriculturafamiliar.service.validator.authentication;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.es.agriculturafamiliar.entity.User;
import com.es.agriculturafamiliar.exception.InvalidCredentialsException;

@ExtendWith(MockitoExtension.class)
public class CredentialsValidatorTests {
	
	@InjectMocks
	private CredentialsValidator credentialsValidator;
	
	@Mock
	private PasswordEncoder passwordEncoder;
	
	private static final User user = User.builder()
			.password("senha")
			.email("myemail@email.com")
			.build();
	

	private static final User loadedUser = User.builder()
			.password("senhaa")
			.email("myemail@email.com")
			.build();
	
	@Test
	public void validate_shouldThrowInvalidCredentialsException_whenPasswordDoesNotMatche() {
		when(passwordEncoder.matches(any(), any())).thenReturn(false);
		assertThrows(InvalidCredentialsException.class, () -> credentialsValidator.validate(user, loadedUser));
	}
	
	@Test
	public void validate_shouldNotThrowException_whenPasswordsMatches() {		
		when(passwordEncoder.matches(any(), any())).thenReturn(true);
		assertDoesNotThrow(() -> credentialsValidator.validate(user, loadedUser));
	}

}
