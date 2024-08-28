package com.az.authenticationservice.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String msg) {
        super(msg);
    }
}
