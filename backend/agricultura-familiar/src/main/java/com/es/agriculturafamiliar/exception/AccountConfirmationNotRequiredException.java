package com.es.agriculturafamiliar.exception;

import org.springframework.http.HttpStatus;

public class AccountConfirmationNotRequiredException extends AuthException {
	
    private static final String EXCEPTION_MESSAGE = "Erro ao confirmar cadastro";
    private static final String EXCEPTION_TITLE = "Conta do usuário não necessita de confirmação e já pode ser acessada normalmente";
    private static final HttpStatus EXCEPTION_STATUS = HttpStatus.UNPROCESSABLE_ENTITY;
    private static final long serialVersionUID = -7652203534985466817L;

	public AccountConfirmationNotRequiredException() {
        super(EXCEPTION_MESSAGE, EXCEPTION_TITLE, EXCEPTION_STATUS);
	}
}
