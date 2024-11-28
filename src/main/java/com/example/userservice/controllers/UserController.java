package com.example.userservice.controllers;

import com.example.userservice.dtos.LoginRequestDto;
import com.example.userservice.dtos.SignUpRequestDto;
import com.example.userservice.dtos.UserResponseDto;
import com.example.userservice.models.Token;
import com.example.userservice.models.User;
import com.example.userservice.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/signup")
    public UserResponseDto signUp
            (@RequestBody SignUpRequestDto signUpRequestDto) {
        User user = userService.signUp(signUpRequestDto.getEmail(),
                signUpRequestDto.getName(), signUpRequestDto.getPassword());

        return  UserResponseDto.from(user);
    }

    @PostMapping("/login")
    public Token login(@RequestBody LoginRequestDto signInRequestDto){
       return userService.login(signInRequestDto.getEmail(), signInRequestDto.getPassword());
    }

    @GetMapping("/validate/{token}")
    public UserResponseDto validateToken(@PathVariable String token){
        User user = userService.validateToken(token);
        return  UserResponseDto.from(user);
    }
}
