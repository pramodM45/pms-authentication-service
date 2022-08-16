package com.example.authenticationmicroservice.model;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUser {

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<AppRole> roles;

    @Override
    public String toString() {
        return "AppUser{" +
                "email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }
}
