package com.dimasukimas.exception;

public class UserAuthenticationFailedException extends RuntimeException{
    public UserAuthenticationFailedException(String message) {
        super(message);
    }
}
