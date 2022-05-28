package com.weedro.authentication.service.exception;

public class WrongUserCredentialsException extends
    RuntimeException {

    public WrongUserCredentialsException(String message) {
        super(message);
    }
}
