package com.example.authenticationmicroservice.repo;


import com.example.authenticationmicroservice.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser,Long> {
    AppUser findByEmail(String email);
}
