package com.example.authenticationmicroservice.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class AuthenticationRequest {
    private String userName;
    private String password;
}
