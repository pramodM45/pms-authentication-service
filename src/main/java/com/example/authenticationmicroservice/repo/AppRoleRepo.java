package com.example.authenticationmicroservice.repo;


import com.example.authenticationmicroservice.model.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepo extends JpaRepository<AppRole,Long> {
}
