package com.example.authenticationmicroservice.controller;

import com.example.authenticationmicroservice.exceptions.JWTExpiredException;
import com.example.authenticationmicroservice.exceptions.PasswordInvalidException;
import com.example.authenticationmicroservice.model.AuthenticationResponse;
import com.example.authenticationmicroservice.model.JWTValidateResponse;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class JWTControllerAdvice {

    @ExceptionHandler(value = PasswordInvalidException.class)
    public ResponseEntity<AuthenticationResponse> passwordInvalidExceptionHandler(PasswordInvalidException e){
        AuthenticationResponse response = AuthenticationResponse.builder().message(e.getMessage()).build();
        return new ResponseEntity<>(response,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<AuthenticationResponse> usernameNotFoundExceptionHandler(UsernameNotFoundException e){
        AuthenticationResponse response = AuthenticationResponse.builder().message(e.getMessage()).build();
        return new ResponseEntity<>(response,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = SignatureException.class)
    public ResponseEntity<JWTValidateResponse> JWTSignatureExceptionHandler(SignatureException e){
        JWTValidateResponse response = JWTValidateResponse.builder().message(e.getMessage()).build();
        return new ResponseEntity<>(response,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = JWTExpiredException.class)
    public ResponseEntity<JWTValidateResponse> JWTExpiredExceptionHandler(JWTExpiredException e){
        JWTValidateResponse response = JWTValidateResponse.builder().message(e.getMessage()).build();
        return new ResponseEntity<>(response,HttpStatus.UNAUTHORIZED);
    }
}

