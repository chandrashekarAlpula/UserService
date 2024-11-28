package com.example.userservice.services;

import com.example.userservice.models.Token;
import com.example.userservice.models.User;
import com.example.userservice.repos.TokenRepo;
import com.example.userservice.repos.UserRepo;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepo userRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private  TokenRepo tokenRepo;

    public UserService(UserRepo userRepo, BCryptPasswordEncoder bCryptPasswordEncoder, TokenRepo tokenRepo) {
        this.userRepo = userRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepo = tokenRepo;
    }
    public User signUp(String email, String username, String password) {
        //If user is already present throw exception
        User user = new User();
        user.setEmail(email);
        user.setName(username);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        return  userRepo.save(user);

    }

    public Token login(String email, String password) {
        //Email should be present
        //Its delete flag must be false
        //Its not expired

        //Check if user with received email exists in DB
        Optional<User> user = userRepo.findByEmail(email);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("User with email " + email + " is not found");
        }

        //Check if password matches
        if(!bCryptPasswordEncoder.matches(password, user.get().getPassword())){
            throw  new BadCredentialsException("Wrong password entered for email " + email);
        }

        //Generate Token
        Token token =  GenerateToken(user.get());
        return tokenRepo.save(token);
    }

    private Token GenerateToken(User user) {
        Token token = new Token();
        token.setValue(RandomStringUtils.randomAlphanumeric(10));
        token.setExpiryAt(System.currentTimeMillis() + 3600000);
        token.setUser(user);
        return token;
    }

    public User validateToken(String tokenStr) {
        Optional<Token> token = tokenRepo.findByValueAndDeletedAndExpiryAtGreaterThan(tokenStr,
                false, System.currentTimeMillis());

        if(token.isEmpty()){
            throw new BadCredentialsException("Invalid token");
        }

        return token.get().getUser();
    }
}
