package com.es.agriculturafamiliar.service.validator.authentication;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.es.agriculturafamiliar.entity.ConfirmacaoCadastro;
import com.es.agriculturafamiliar.entity.User;
import com.es.agriculturafamiliar.exception.AccountNotEnabledException;

@ExtendWith(MockitoExtension.class)
public class UserEnabledValidatorTests {
	
	@InjectMocks
	private UserEnabledValidator userEnabledValidator;
	
	private static final User user = User.builder()
			.password("senha")
			.email("myemail@email.com")
			.build();
	

	private static final User loadedUser = User.builder()
			.password("senhaa")
			.email("myemail@email.com")
			.build();

	@Test
	public void validate_shoudlThrowAccountNotEnabledException_whenUserAccountIsNotEnabled() {
		loadedUser.setEnabled(false);
		assertThrows(AccountNotEnabledException.class, () -> userEnabledValidator.validate(user, loadedUser));
		
	}
	
	@Test
	public void validate_shoudlNotThrowException_whenUserAccountIsEnabled() {
		loadedUser.setEnabled(true);
		assertDoesNotThrow(() -> userEnabledValidator.validate(user, loadedUser));		
	}
}
