package com.es.agriculturafamiliar.exception;

import org.springframework.http.HttpStatus;

public class CodigoConfirmacaoInvalidoException extends AuthException {

	private static final long serialVersionUID = -8131995833631456967L;
    private static final String EXCEPTION_MESSAGE = "Erro ao confirmar cadastro";
    private static final String EXCEPTION_TITLE = "Código de confirmação fornecido é inválido";
    private static final HttpStatus EXCEPTION_STATUS = HttpStatus.UNPROCESSABLE_ENTITY;

	public CodigoConfirmacaoInvalidoException() {
        super(EXCEPTION_MESSAGE, EXCEPTION_TITLE, EXCEPTION_STATUS);
	}

}
