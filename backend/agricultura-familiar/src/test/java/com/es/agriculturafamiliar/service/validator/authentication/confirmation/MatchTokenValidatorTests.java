package com.es.agriculturafamiliar.service.validator.authentication.confirmation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.es.agriculturafamiliar.entity.ConfirmacaoCadastro;
import com.es.agriculturafamiliar.entity.User;
import com.es.agriculturafamiliar.exception.CodigoConfirmacaoException;

@ExtendWith(MockitoExtension.class)
public class MatchTokenValidatorTests {
	
	@InjectMocks
	private MatchTokenValidator matchTokenValidator;
		
	@Test
	public void validate_shouldThrowCodigoConfirmacaoInvalidoException_whenCodeDoesNotMatch() {
		User user = User.builder()
			.confirmacaoCadastro(ConfirmacaoCadastro.builder().codigo("132156").build())
			.build();
		
		String token = "421313";
		
		assertThrows(CodigoConfirmacaoException.class, () -> matchTokenValidator.validate(user, token));
	}
	
	@Test
	public void validate_shouldNotThrowException_whenCodeAndTokenMatches() {
		User user = User.builder()
				.confirmacaoCadastro(ConfirmacaoCadastro.builder().codigo("132156").build())
				.build();
		
		String token = "132156";
		assertDoesNotThrow(() -> matchTokenValidator.validate(user, token));		
	}

}
