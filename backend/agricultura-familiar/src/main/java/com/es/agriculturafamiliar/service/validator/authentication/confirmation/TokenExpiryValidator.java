package com.es.agriculturafamiliar.service.validator.authentication.confirmation;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Component;

import com.es.agriculturafamiliar.entity.ConfirmacaoCadastro;
import com.es.agriculturafamiliar.entity.User;
import com.es.agriculturafamiliar.exception.AuthException;
import com.es.agriculturafamiliar.exception.CodigoConfirmacaoExpiradoException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TokenExpiryValidator implements IAccountConfirmationValidator {
	private Long CODIGO_CONFIRMACAO_VALIDITY_IN_MINUTES = 30L;

	@Override
	public void validate(User user, String token) throws AuthException {
		ConfirmacaoCadastro confirmacaoCadastro = user.getConfirmacaoCadastro();
		LocalDateTime dataCriacao = confirmacaoCadastro.getDataCriacao();
		Instant now = Instant.now().truncatedTo(ChronoUnit.SECONDS);
		Instant issuedAt = dataCriacao.toInstant(ZoneOffset.UTC);
		Instant expiresAt = issuedAt.plus(CODIGO_CONFIRMACAO_VALIDITY_IN_MINUTES, ChronoUnit.MINUTES);

		if (now.isAfter(expiresAt)) {
			log.info("Código de confirmação expirado");
			throw new CodigoConfirmacaoExpiradoException();
		}

		log.info("Código de confirmação não expirado");
	}

}