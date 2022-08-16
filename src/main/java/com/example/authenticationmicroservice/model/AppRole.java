package com.example.authenticationmicroservice.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppRole implements GrantedAuthority {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<AppUser> users;

    @Override
    public String getAuthority() {
        return name;
    }

    @Override
    public String toString() {
        return "AppRole{" +
                "name='" + name + '\'' +
                '}';
    }
}