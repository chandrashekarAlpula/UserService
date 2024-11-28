package com.example.userservice.dtos;

import com.example.userservice.models.Role;
import com.example.userservice.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponseDto {
    private String email;
    private String name;
    private List<Role> roles;

    public UserResponseDto(User user) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.roles = user.getRoles();
    }

    public static UserResponseDto from(User user) {
        return new UserResponseDto(user);
    }
}
