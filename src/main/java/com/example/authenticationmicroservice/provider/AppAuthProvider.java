package com.example.authenticationmicroservice.provider;

import com.example.authenticationmicroservice.exceptions.PasswordInvalidException;
import com.example.authenticationmicroservice.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AppAuthProvider implements AuthenticationProvider {

    @Autowired
    UserDetailsServiceImpl userDetailsService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if(new BCryptPasswordEncoder().matches(password,userDetails.getPassword())){
            return new UsernamePasswordAuthenticationToken(username,password,userDetails.getAuthorities());
        }else{
            throw new PasswordInvalidException("Invalid Password");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
