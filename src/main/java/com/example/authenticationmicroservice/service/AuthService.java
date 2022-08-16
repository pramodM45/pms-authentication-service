package com.example.authenticationmicroservice.service;

import com.example.authenticationmicroservice.model.AuthenticationRequest;
import com.example.authenticationmicroservice.model.JWTValidateRequest;
import com.example.authenticationmicroservice.model.JWTValidateResponse;
import com.example.authenticationmicroservice.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    public AuthenticationManager authenticationManager;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    JWTUtils jwtUtils;

    public String authenticateUser(AuthenticationRequest request){
        String token = "";
        String username = request.getUserName();
        String password = request.getPassword();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities())
        );
        if(authentication.isAuthenticated()){
            token = jwtUtils.generateToken(request);
        }
        return token;
    }

    public JWTValidateResponse validateToken(JWTValidateRequest request){
        String token = request.getToken();
        return jwtUtils.validateToken(token);
    }
}
