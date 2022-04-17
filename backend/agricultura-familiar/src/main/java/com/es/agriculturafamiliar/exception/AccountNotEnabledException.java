package com.es.agriculturafamiliar.exception;

import org.springframework.http.HttpStatus;

public class AccountNotEnabledException extends AuthException {
	
    private static final String EXCEPTION_MESSAGE = "Conta ainda não habilitada";
    private static final String EXCEPTION_TITLE = "O usuário ainda não ativou a sua conta";
    private static final HttpStatus EXCEPTION_STATUS = HttpStatus.FORBIDDEN;
	private static final long serialVersionUID = 3819276084177397667L;

	public AccountNotEnabledException() {
		super(EXCEPTION_MESSAGE, EXCEPTION_TITLE, EXCEPTION_STATUS);
	}


}
