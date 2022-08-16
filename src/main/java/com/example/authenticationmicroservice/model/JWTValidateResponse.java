package com.example.authenticationmicroservice.model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JWTValidateResponse {
    private String username;
    private boolean isValid;
    private String message;
}
