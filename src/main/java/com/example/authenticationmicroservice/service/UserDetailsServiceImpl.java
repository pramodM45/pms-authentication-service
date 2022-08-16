package com.example.authenticationmicroservice.service;

import com.example.authenticationmicroservice.model.AppUser;
import com.example.authenticationmicroservice.repo.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AppUserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepo.findByEmail(username);
        if(user==null){
            throw new UsernameNotFoundException("username not found "+username);
        }
        return new User(user.getEmail(),user.getPassword(),user.getRoles());
    }
}
