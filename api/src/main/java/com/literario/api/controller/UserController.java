package com.literario.api.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.literario.api.service.ReviewService; 
import com.literario.api.model.UserEntity;

import com.literario.api.model.ReviewEntity;

import com.literario.api.repo.UserRepo;
import com.literario.api.repo.ReviewRepo;
import com.literario.api.repo.BookRepo;

import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepo userRepo;
    private ReviewService reviewService;

    public UserController(UserRepo userRepo, BookRepo bookRepo, ReviewRepo reviewRepo) {
        this.userRepo = userRepo;
        this.reviewService = new ReviewService(reviewRepo, bookRepo, userRepo);
    }

    @GetMapping()
    public String findAll() {
        return userRepo.findAll().toString();
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<UserEntity> getUserInfo(@PathVariable("id") UUID userId) {
        System.out.println("Recebido UUID: " + userId);
        return userRepo.findById(userId)
            .map(user -> ResponseEntity.ok().body(user))
            .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/{id}/reviews")
    public ResponseEntity<List<ReviewEntity>> getReviews(@PathVariable("id") UUID userId) {
        return reviewService.getReviewsByUser(userId);
    }
}