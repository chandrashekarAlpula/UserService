package com.example.userservice.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.beans.Encoder;

@Configuration
public class EncoderConfig {
    @Bean
    public BCryptPasswordEncoder getBCryptPassWordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
