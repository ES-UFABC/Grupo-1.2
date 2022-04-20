package com.es.agriculturafamiliar.service.validator.authentication.confirmation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;

import com.es.agriculturafamiliar.entity.ConfirmacaoCadastro;
import com.es.agriculturafamiliar.entity.User;
import com.es.agriculturafamiliar.exception.CodigoConfirmacaoExpiradoException;

@ExtendWith(MockitoExtension.class)
@TestPropertySource(properties = { "conformationCodeValidityInMinutes=20" })
public class TokenExpiryValidatorTests {
	
	@InjectMocks
	private TokenExpiryValidator tokenExpiryValidator;
	
	@BeforeEach
	private void setup() {
		ReflectionTestUtils.setField(tokenExpiryValidator, "conformationCodeValidityInMinutes", 20L);
	}
	
	@Test
	public void validate_shouldThrowCodigoConfirmacaoExpiradoException_whenTokenIsExpired() {		
		Timestamp creationInstant = Timestamp.from(Instant.now().truncatedTo(ChronoUnit.SECONDS).minus(30 ,ChronoUnit.MINUTES));
		User user = User
			.builder()
			.confirmacaoCadastro(ConfirmacaoCadastro.builder()
					.dataAtualizacao(creationInstant).build())
			.build();
		
		String token = "123456";
					
		assertThrows(CodigoConfirmacaoExpiradoException.class, () -> tokenExpiryValidator.validate(user, token));
	}
	
	@Test
	public void validate_shouldNotThrowException_whenTokenIsNotExpired() {		
		Timestamp creationInstant = Timestamp.from(Instant.now().truncatedTo(ChronoUnit.SECONDS));
		User user = User
				.builder()
				.confirmacaoCadastro(ConfirmacaoCadastro.builder()
						.dataAtualizacao(creationInstant).build())
				.build();
		
		String token = "123456";
		
		assertDoesNotThrow(() -> tokenExpiryValidator.validate(user, token));
	}

}
