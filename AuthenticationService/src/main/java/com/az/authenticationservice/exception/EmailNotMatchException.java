package com.az.authenticationservice.exception;

public class EmailNotMatchException extends RuntimeException{
    public EmailNotMatchException(String message){
        super(message);
    }
}
