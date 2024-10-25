package com.literario.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.literario.api.model.NotAuthedUserEntity;
import com.literario.api.repo.UserRepo;
import com.literario.api.service.AuthService;
import com.literario.api.service.PasswordService;

import java.util.Map;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;
    private PasswordService passwordService;

    public AuthController(UserRepo userRepo) {
        this.passwordService = new PasswordService();
        this.authService = new AuthService(userRepo, passwordService);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody NotAuthedUserEntity notAuthedUser) {
        return authService.login(notAuthedUser);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody NotAuthedUserEntity notAuthedUser) {
        return authService.registerUser(notAuthedUser);
    }

}
