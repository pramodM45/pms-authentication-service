package com.example.authenticationmicroservice.exceptions;

public class JWTExpiredException extends RuntimeException {
    public JWTExpiredException(String message) {
        super(message);
    }
}
