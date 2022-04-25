package com.es.agriculturafamiliar.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadySignedUpException extends AuthException {

	private static final long serialVersionUID = -4481130699047365341L;
    private static final String EXCEPTION_TITLE = "Usuário já registrado";
    private static final String EXCEPTION_MESSAGE = "O usuário com o email fornecido já está cadastrado no sistema";
    private static final HttpStatus EXCEPTION_STATUS = HttpStatus.UNPROCESSABLE_ENTITY;    

    public UserAlreadySignedUpException() {
        super(EXCEPTION_MESSAGE, EXCEPTION_TITLE, EXCEPTION_STATUS);
    }
}
