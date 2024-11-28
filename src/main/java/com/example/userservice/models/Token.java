package com.example.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Token extends BaseModel{
    private String value;
    private long expiryAt;
    private boolean deleted;

    @ManyToOne
    private User user;
}
