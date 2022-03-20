package com.es.agriculturafamiliar.exception;

public class SendMessageException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SendMessageException(String message) {
        super(message);
    }

}
