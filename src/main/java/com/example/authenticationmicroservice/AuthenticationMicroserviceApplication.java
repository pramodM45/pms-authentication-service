package com.example.authenticationmicroservice;

import com.example.authenticationmicroservice.model.AppRole;
import com.example.authenticationmicroservice.model.AppUser;
import com.example.authenticationmicroservice.repo.AppRoleRepo;
import com.example.authenticationmicroservice.repo.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.Arrays;
import java.util.HashSet;

@SpringBootApplication
public class AuthenticationMicroserviceApplication implements CommandLineRunner {

    @Autowired
    AppRoleRepo appRoleRepo;

    @Autowired
    AppUserRepo appUserRepo;

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationMicroserviceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        AppRole role = AppRole.builder().name("ROLE_ADMIN").build();
        appRoleRepo.save(role);
        AppUser user = AppUser.builder().email("pramod@gmail.com").roles(new HashSet<AppRole>(Arrays.asList(role)))
                .firstName("pramod").lastName("kumar").password("$2a$10$skuElwoFXBVrT2k6KVZnredzv6EXftW.GosGJw2qRsk2WU1WFVTuW")
                .build();
        appUserRepo.save(user);
    }
}
