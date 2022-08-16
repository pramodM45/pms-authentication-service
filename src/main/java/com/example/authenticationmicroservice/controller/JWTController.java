package com.example.authenticationmicroservice.controller;

import com.example.authenticationmicroservice.model.AuthenticationRequest;
import com.example.authenticationmicroservice.model.AuthenticationResponse;
import com.example.authenticationmicroservice.model.JWTValidateRequest;
import com.example.authenticationmicroservice.model.JWTValidateResponse;
import com.example.authenticationmicroservice.service.AuthService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class JWTController {

    @Autowired
    AuthService authService;

    @CrossOrigin("http://localhost:4200")
    @PostMapping ("/authenticate")
    @ApiOperation(value = "authenticate using username and password")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        String token = authService.authenticateUser(request);
        return new ResponseEntity<>(AuthenticationResponse.builder().token(token)
                .username(request.getUserName()).message("Successfully Authenticated").build(), HttpStatus.OK);
    }

    @PostMapping("/validate")
    @ApiOperation("validate the jwt token")
    public ResponseEntity<JWTValidateResponse> validateToken(@RequestBody JWTValidateRequest request){
        JWTValidateResponse response = authService.validateToken(request);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/health")
    public ResponseEntity<?> healthCheck(){
        HashMap<String,String> map = new HashMap<>();
        map.put("Status","UP");
        return ResponseEntity.ok(map);
    }


}
