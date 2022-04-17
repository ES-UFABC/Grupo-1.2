package com.es.agriculturafamiliar.service.validator.authentication;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.es.agriculturafamiliar.entity.ConfirmacaoCadastro;
import com.es.agriculturafamiliar.entity.User;
import com.es.agriculturafamiliar.exception.AccountNotEnabledException;
import com.es.agriculturafamiliar.exception.AuthException;
import com.es.agriculturafamiliar.service.ConfirmacaoCadastroService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class UserEnabledValidator implements IAuthenticationValidator<User, User> {

	@Override
	public void validate(User user, User loadedUser) throws AuthException {		
		if (loadedUser.isEnabled()) {
			log.info("Conta do usuário já está habilitada para uso");
			return;									
		}
		
		log.info("Conta do usuário ainda não teve o cadastro confirmado");
		throw new AccountNotEnabledException();		
	}	

}
