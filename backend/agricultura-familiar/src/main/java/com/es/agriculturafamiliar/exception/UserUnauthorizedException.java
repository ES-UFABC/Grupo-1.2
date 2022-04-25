package com.es.agriculturafamiliar.exception;

import org.springframework.http.HttpStatus;

public class UserUnauthorizedException extends AuthException {

    private static final String EXCEPTION_MESSAGE = "Usuário não autorizado";
    private static final String EXCEPTION_TITLE = "Usuário não possui permissões suficientes para realizar a operação";
    private static final HttpStatus EXCEPTION_STATUS = HttpStatus.UNAUTHORIZED;    

    public UserUnauthorizedException() {
        super(EXCEPTION_MESSAGE, EXCEPTION_TITLE, EXCEPTION_STATUS);
    }
    
    public UserUnauthorizedException(String message) {
    	super(message, EXCEPTION_TITLE, EXCEPTION_STATUS);
    }
    

	private static final long serialVersionUID = 6870277232907723867L;

}
