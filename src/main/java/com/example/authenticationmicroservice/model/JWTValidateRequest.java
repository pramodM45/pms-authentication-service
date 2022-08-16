package com.example.authenticationmicroservice.model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JWTValidateRequest {
    private String token;
}
