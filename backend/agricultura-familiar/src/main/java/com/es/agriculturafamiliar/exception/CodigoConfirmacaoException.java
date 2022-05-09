package com.es.agriculturafamiliar.exception;

import org.springframework.http.HttpStatus;

public class CodigoConfirmacaoException extends AuthException {
    private static final String EXCEPTION_MESSAGE = "Erro ao confirmar cadastro";
    private static final String EXCEPTION_TITLE = "Código de confirmação está expirado ou é inválido, por favor gere outro";
    private static final HttpStatus EXCEPTION_STATUS = HttpStatus.UNPROCESSABLE_ENTITY;
	private static final long serialVersionUID = 4914000635541828264L;

	public CodigoConfirmacaoException() {
        super(EXCEPTION_MESSAGE, EXCEPTION_TITLE, EXCEPTION_STATUS);
	}

}
