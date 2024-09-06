package com.literario.api.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.literario.api.service.UserService;
import com.literario.api.service.PasswordService;

import com.literario.api.model.UserEntity;
import com.literario.api.model.NotAuthedUserEntity;
import com.literario.api.repo.UserRepo;
import com.literario.api.repo.ReviewRepo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepo userRepo;
    private ReviewRepo reviewRepo;
    private UserService userService;
    private PasswordService passwordService;

    public UserController(UserRepo userRepo, ReviewRepo reviewRepo) {
        this.userRepo = userRepo;
        this.reviewRepo = reviewRepo;
        this.userService = new UserService(userRepo);
        this.passwordService = new PasswordService();

    }

    @GetMapping()
    public String findAll() {
        return userRepo.findAll().toString();
    }

    @GetMapping("/{password}")
    public String hashPassword(@PathVariable String password) {
        return passwordService.hashPassword(password);
    }

    @PostMapping("/register")
    public ResponseEntity<UserEntity> registerUser(@RequestBody NotAuthedUserEntity notAuthedUser) {
        return userService.registerUser(notAuthedUser);
    }

    @GetMapping("/{id}/reviews")
    public String getReviewsByUser(@PathVariable("id") UUID userId) {
        try {
        return reviewRepo.findReviewsByUser(userId).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
    
}