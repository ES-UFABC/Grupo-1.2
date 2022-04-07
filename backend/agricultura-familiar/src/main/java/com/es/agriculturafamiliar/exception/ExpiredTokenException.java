package com.es.agriculturafamiliar.exception;

import org.springframework.http.HttpStatus;

public class ExpiredTokenException extends AuthException {
    private static final long serialVersionUID = 523123123123L;

    private static final String EXCEPTION_MESSAGE = "Token fornecido está expirado";
    private static final String EXCEPTION_TITLE = "Token expirado";
    private static final HttpStatus EXCEPTION_STATUS = HttpStatus.UNAUTHORIZED;  

    public ExpiredTokenException() {
        super(EXCEPTION_MESSAGE, EXCEPTION_TITLE, EXCEPTION_STATUS);
    }
    
}
