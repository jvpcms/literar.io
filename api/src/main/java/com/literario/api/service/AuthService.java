package com.literario.api.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.ExpiredJwtException;

import java.util.Date;
import java.util.Map;
import java.util.List;
import java.util.UUID;

import com.literario.api.model.NotAuthedUserEntity;
import com.literario.api.model.UserEntity;
import com.literario.api.repo.UserRepo;
import com.literario.api.utils.CustomResponse;

@Service
public class AuthService {
    
    private AuthService() {}

    private static final String SECRET_KEY = System.getProperty("HS256_SECRET");
    
    private static UserRepo userRepo;
    private static PasswordService passwordService;


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

    public static ResponseEntity<Map<String, String>> login(NotAuthedUserEntity notAuthedUser) {

        // retirve user data from db
        List<UserEntity> usersInDatabase = userRepo.findByUsername(notAuthedUser.getUsername());

        if (usersInDatabase.isEmpty()) {
            return CustomResponse.userNotFound();
        }

        UserEntity user = usersInDatabase.get(0);

        // check if password matches
        Boolean passwordIsValid = passwordService.checkPassword(notAuthedUser.getPassword(), user.getHash());

        if (passwordIsValid == null || !passwordIsValid) {
            return CustomResponse.invalidPassword();
        }

        // generate token
        return CustomResponse.returnToken(genToken(user));
    }
    

    public static Claims extractAllClaims(String token) {
        // throw ExpiredJwtException if token is expired
        // throw SignatureException if signature is invalid
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public static UUID extractUserId(Claims claims) {
        return UUID.fromString(claims.get("user_id").toString());
    }
    

    public static Boolean verifyId(Claims claims, UUID id) {
        return id.equals(extractUserId(claims));
    }
    

    public static ResponseEntity<Map<String, String>> verifyToken(String token, UUID id) {

        Claims claims = null;

        try {
            claims = extractAllClaims(token);
        }
        catch (ExpiredJwtException e) {
            return CustomResponse.tokenExpired();
        }
        catch (SignatureException e) {
            return CustomResponse.invalidSignature();
        }

        if (verifyId(claims, id) == null || !verifyId(claims, id)) {
            return CustomResponse.invalidUserId();
        }
        
        return CustomResponse.ok();

    }
    
}
