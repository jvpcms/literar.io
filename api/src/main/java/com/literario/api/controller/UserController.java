package com.literario.api.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.literario.api.repo.UserRepo;
import com.literario.api.repo.ReviewRepo;


@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepo userRepo;
    private ReviewRepo reviewRepo;

    public UserController(UserRepo userRepo, ReviewRepo reviewRepo) {
        this.userRepo = userRepo;
        this.reviewRepo = reviewRepo;

    }

    @GetMapping()
    public String findAll() {
        return userRepo.findAll().toString();
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