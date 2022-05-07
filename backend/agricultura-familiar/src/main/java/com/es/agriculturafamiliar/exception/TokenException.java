package com.es.agriculturafamiliar.exception;

import org.springframework.http.HttpStatus;

public class TokenException extends AuthException {
    private static final long serialVersionUID = 523123123123L;

    private static final String EXCEPTION_MESSAGE = "Token fornecido está expirado ou é inválido.";
    private static final String EXCEPTION_TITLE = "Token expirado/inválido";
    private static final HttpStatus EXCEPTION_STATUS = HttpStatus.UNAUTHORIZED;  

    public TokenException() {
        super(EXCEPTION_MESSAGE, EXCEPTION_TITLE, EXCEPTION_STATUS);
    }
    
}
