package com.es.agriculturafamiliar.service.validator.authentication.confirmation;

import org.springframework.stereotype.Component;

import com.es.agriculturafamiliar.entity.ConfirmacaoCadastro;
import com.es.agriculturafamiliar.entity.User;

import com.es.agriculturafamiliar.exception.AuthException;
import com.es.agriculturafamiliar.exception.CodigoConfirmacaoException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MatchTokenValidator implements IAccountConfirmationValidator {

	@Override
	public void validate(User user, String token) throws AuthException {
		ConfirmacaoCadastro confirmacaoCadastro = user.getConfirmacaoCadastro();
				
		String codigo = confirmacaoCadastro.getCodigo();
		
		if (!codigo.equals(token)) {
			log.info("Código de confirmação fornecido é diferente do esperado");
			throw new CodigoConfirmacaoException();
		}
		
		log.info("Código de confirmação é igual ao esperado");
	}

}
