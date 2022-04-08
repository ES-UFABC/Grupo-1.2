package com.es.agriculturafamiliar.exception;

import org.springframework.http.HttpStatus;

public class InvalidTokenException extends AuthException {
    private static final long serialVersionUID = 123123123123L;

    private static final String EXCEPTION_MESSAGE = "Token fornecido é inválido";
    private static final String EXCEPTION_TITLE = "Token fornecido é inválido";
    private static final HttpStatus EXCEPTION_STATUS = HttpStatus.UNAUTHORIZED;    

    public InvalidTokenException() {
        super(EXCEPTION_MESSAGE, EXCEPTION_TITLE, EXCEPTION_STATUS);
    }
    
}
