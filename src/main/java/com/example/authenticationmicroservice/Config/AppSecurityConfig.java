package com.example.authenticationmicroservice.Config;


import com.example.authenticationmicroservice.provider.AppAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;

@Configuration
public class AppSecurityConfig {

    @Autowired
    AppAuthProvider appAuthProvider;

    @Bean
    public PasswordEncoder passwordEncoderBean(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {

       return http.csrf().disable().cors().and()
                .authorizeRequests().anyRequest().permitAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().build();

    }

    @Bean
    public AuthenticationManager authenticationManagerBean(){
        return new ProviderManager(Arrays.asList(appAuthProvider));
    }


}
