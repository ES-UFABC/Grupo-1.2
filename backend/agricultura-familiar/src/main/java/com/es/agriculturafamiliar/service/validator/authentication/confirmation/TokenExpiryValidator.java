package com.es.agriculturafamiliar.service.validator.authentication.confirmation;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.es.agriculturafamiliar.entity.ConfirmacaoCadastro;
import com.es.agriculturafamiliar.entity.User;
import com.es.agriculturafamiliar.exception.AuthException;
import com.es.agriculturafamiliar.exception.CodigoConfirmacaoException;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Data
public class TokenExpiryValidator implements IAccountConfirmationValidator {
	private Long CODIGO_CONFIRMACAO_VALIDITY_IN_MINUTES = 30L;
	
	@Value("${security.confirmation-code.duration-in-minutes}")
	private Long conformationCodeValidityInMinutes;

	@Override
	public void validate(User user, String token) throws AuthException {
		
		ConfirmacaoCadastro confirmacaoCadastro = user.getConfirmacaoCadastro();
		Instant issuedAt = confirmacaoCadastro.getDataAtualizacao().toInstant();

		Instant now = Instant.now().truncatedTo(ChronoUnit.SECONDS);
		Instant expiresAt = issuedAt.plus(conformationCodeValidityInMinutes, ChronoUnit.MINUTES);


		if (now.isAfter(expiresAt)) {
			log.info("Código de confirmação expirado");
			throw new CodigoConfirmacaoException();
		}

		log.info("Código de confirmação não expirado");
	}

}
