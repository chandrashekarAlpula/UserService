package com.example.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class User extends BaseModel{
    private String email;
    private String name;
    private String password;
    private boolean isEmailVerified;
    @OneToMany
    private List<Role> roles;
}
