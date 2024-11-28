package com.example.userservice.repos;

import com.example.userservice.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepo extends JpaRepository<Token, Long> {

    Optional<Token> findByValueAndDeletedAndExpiryAtGreaterThan(String token, boolean b, long l);
}
