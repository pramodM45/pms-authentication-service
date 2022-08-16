package com.example.authenticationmicroservice.controller;

import com.example.authenticationmicroservice.model.AuthenticationRequest;
import com.example.authenticationmicroservice.model.AuthenticationResponse;
import com.example.authenticationmicroservice.model.JWTValidateRequest;
import com.example.authenticationmicroservice.model.JWTValidateResponse;
import com.example.authenticationmicroservice.repo.AppRoleRepo;
import com.example.authenticationmicroservice.repo.AppUserRepo;
import com.example.authenticationmicroservice.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(JWTController.class)
@AutoConfigureMockMvc(addFilters = false)
class JWTControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppRoleRepo appRoleRepo;

    @MockBean
    private AppUserRepo appUserRepo;

    @MockBean
    private AuthService authService;

    @Test
    void authenticate() throws Exception{
        when(authService.authenticateUser(any())).thenReturn("test_token");

        RequestBuilder request = MockMvcRequestBuilders
                .post("/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(AuthenticationRequest.builder()
                        .userName("test_username").password("test_password").build()));

        mockMvc.perform(request).andExpect(status().isOk());
    }

    @Test
    void validateToken() throws Exception{
        when(authService.validateToken(any())).thenReturn(JWTValidateResponse.builder()
                .isValid(true).build());

        RequestBuilder request = MockMvcRequestBuilders
                .post("/validate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(JWTValidateRequest.builder().token("test_token").build()));
        mockMvc.perform(request).andExpect(status().isOk());
    }
}