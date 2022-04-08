package com.es.agriculturafamiliar.exception;

import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends AuthException {
    private static final long serialVersionUID = 723123123123L;

    private static final String EXCEPTION_TITLE = "Credenciais inválidas";
    private static final String EXCEPTION_MESSAGE = "As credenciais fornecidas são inválidas para o usuário especificado";
    private static final HttpStatus EXCEPTION_STATUS = HttpStatus.UNAUTHORIZED;    

    public InvalidCredentialsException() {
        super(EXCEPTION_MESSAGE, EXCEPTION_TITLE, EXCEPTION_STATUS);
    }
}
