package com.literario.api.service;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Map;
import java.util.HashMap;
import java.util.Date;
import java.util.function.Function;

import com.literario.api.model.User;

@Service
public class AuthService {

    private static final String SECRET_KEY = "secret";

    public static String genToken(User user) {

        long currentTimeMillis = System.currentTimeMillis();
        long expirationTimeMillis = currentTimeMillis + 1000 * 60 * 60 * 24 * 7; // 1 week

        return (
            Jwts.builder()
            .setHeaderParam("alg", "HS256")        // Algorithm
            .setHeaderParam("typ", "JWT")          // Type
            .setSubject(user.getUsername())                   // Subject
            .claim("user_id", user.getId())              // Custom claim
            .setIssuedAt(new Date(currentTimeMillis))         // Issued at
            .setExpiration(new Date(expirationTimeMillis))    // Expiration
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)   // Signature
            .compact()
        );
    }

    public Boolean verifyToken(String token) {
        return true;
    }

}
