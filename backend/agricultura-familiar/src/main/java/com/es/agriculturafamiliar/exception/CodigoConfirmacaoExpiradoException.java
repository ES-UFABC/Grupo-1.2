package com.es.agriculturafamiliar.exception;

import org.springframework.http.HttpStatus;

public class CodigoConfirmacaoExpiradoException extends AuthException {
    private static final String EXCEPTION_MESSAGE = "Erro ao confirmar cadastro";
    private static final String EXCEPTION_TITLE = "Código de confirmação está expirado, por favor gere outro";
    private static final HttpStatus EXCEPTION_STATUS = HttpStatus.UNPROCESSABLE_ENTITY;
	private static final long serialVersionUID = 4914000635541828264L;

	public CodigoConfirmacaoExpiradoException() {
        super(EXCEPTION_MESSAGE, EXCEPTION_TITLE, EXCEPTION_STATUS);
	}

}
