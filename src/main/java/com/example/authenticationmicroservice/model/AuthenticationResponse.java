package com.example.authenticationmicroservice.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class AuthenticationResponse {
    private String username;
    private String token;
    private String message;
}
