package com.literario.api.service;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Map;
import java.util.HashMap;
import java.util.Date;
import java.util.function.Function;
import java.util.UUID;

import com.literario.api.model.UserEntity;

@Service
public class AuthService {

    private static final String SECRET_KEY = System.getProperty("HS256_SECRET");

    public static String genToken(UserEntity user) {

        long currentTimeMillis = System.currentTimeMillis();
        long expirationTimeMillis = currentTimeMillis + 1000 * 60 * 60;

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

    public static Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public static String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public static UUID extractUserId(String token) {
        return extractClaim(token, claims -> UUID.fromString((String) claims.get("user_id")));
    }

    public static Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public static Date extractIssuedAt(String token) {
        return extractClaim(token, Claims::getIssuedAt);
    }

    public static Boolean verifyToken(String token) {
        return true;
    }

}
