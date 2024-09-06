package com.literario.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.literario.api.model.NotAuthedUserEntity;
import com.literario.api.model.UserEntity;
import com.literario.api.repo.UserRepo;
import com.literario.api.service.AuthService;
import com.literario.api.service.PasswordService;

import java.util.List;
import java.util.Map;
import java.util.HashMap;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final String FALSE = "false";
    private static final String MESSAGE = "message";

    private UserRepo userRepo;
    private PasswordService passwordService;

    public AuthController(UserRepo userRepo) {
        this.userRepo = userRepo;
        this.passwordService = new PasswordService();
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody NotAuthedUserEntity notAuthedUser) {

        Map<String, String> responseBody = new HashMap<>();

        // retirve user data from db
        List<UserEntity> usersInDatabase = userRepo.findByUsername(notAuthedUser.getUsername());

        if (usersInDatabase.isEmpty()) {
            responseBody.put("ok", FALSE);
            responseBody.put(MESSAGE, "User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
        }

        UserEntity user = usersInDatabase.get(0);

        // check if password matches
        Boolean passwordIsValid = passwordService.checkPassword(notAuthedUser.getPassword(), user.getHash());

        if (passwordIsValid == null || !passwordIsValid) {
            responseBody.put("ok", FALSE);
            responseBody.put(MESSAGE, "Invalid password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
        }

        // generate token
        responseBody.put("ok", "true");
        responseBody.put("token", AuthService.genToken(user));
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);

    }

    @PostMapping("/validate")
    public ResponseEntity<Map<String, String>> validate(@RequestBody String token) {

        Map<String, String> responseBody = new HashMap<>();

        Boolean tokenIsValid = AuthService.verifyToken(token);

        if (tokenIsValid == null || !tokenIsValid) {
            responseBody.put("ok", FALSE);
            responseBody.put(MESSAGE, "Invalid token");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
        }

        responseBody.put("ok", "true");
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @PostMapping("/check")
    public String check(@RequestHeader("token") String token) {
        String id = AuthService.extractUserId(token).toString();
        String username = AuthService.extractUsername(token);
        String expiration = AuthService.extractExpiration(token).toString();
        String issuedAt = AuthService.extractIssuedAt(token).toString();

        return "User ID: " + id + "\nUsername: " + username + "\nExpiration: " + expiration + "\nIssued At: " + issuedAt;
    }
}
