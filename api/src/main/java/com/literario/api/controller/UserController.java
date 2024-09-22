package com.literario.api.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;
import java.util.Map;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.literario.api.service.UserService;
import com.literario.api.service.PasswordService;
import com.literario.api.service.ReviewService; 

import com.literario.api.model.ReviewEntity;

import com.literario.api.model.NotAuthedUserEntity;
import com.literario.api.repo.UserRepo;
import com.literario.api.repo.ReviewRepo;
import com.literario.api.repo.BookRepo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepo userRepo;
    private UserService userService;
    private PasswordService passwordService;
    private ReviewService reviewService;

    public UserController(UserRepo userRepo, BookRepo bookRepo, ReviewRepo reviewRepo) {
        this.userRepo = userRepo;
        this.userService = new UserService(userRepo);
        this.passwordService = new PasswordService();
        this.reviewService = new ReviewService(reviewRepo, bookRepo, userRepo);
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
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody NotAuthedUserEntity notAuthedUser) {
        return userService.registerUser(notAuthedUser);
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<List<ReviewEntity>> getReviews(@PathVariable("id") UUID userId) {
        return reviewService.getReviewsByUser(userId);
    }
}