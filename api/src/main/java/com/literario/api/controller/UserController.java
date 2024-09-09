package com.literario.api.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.literario.api.service.UserService;
import com.literario.api.service.PasswordService;
import com.literario.api.service.ReviewService; // Add this import

import com.literario.api.model.User;
import com.literario.api.repo.UserRepo;
import com.literario.api.repo.ReviewRepo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepo userRepo;
    private UserService userService;
    private PasswordService passwordService;
    private ReviewService reviewService;

    public UserController(UserRepo userRepo, ReviewRepo reviewRepo) {
        this.userRepo = userRepo;
        this.userService = new UserService(userRepo);
        this.passwordService = new PasswordService();
        this.reviewService = new ReviewService(reviewRepo);
    }

    @GetMapping()
    public String findAll() {
        return userRepo.findAll().toString();
    }

    @GetMapping("/{password}")
    public String hashPassword(@PathVariable String password) {
        return passwordService.hashPassword(password).toString();
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @GetMapping("/{id}/reviews")
    public String getReviews(@PathVariable("id") UUID userId) {
        return reviewService.getReviewsByUser(userId).toString();
    }
}