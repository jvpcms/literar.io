package com.literario.api.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.literario.api.repo.ReviewRepo;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UsersController {
    
    private ReviewRepo reviewRepo;

    public UsersController(ReviewRepo reviewRepo) {
        this.reviewRepo = reviewRepo;
    }

    @GetMapping("/{id}/reviews")
    public String getReviewsByUser(@PathVariable("id") String userId) {
        UUID userUUID = UUID.fromString(userId);
        return reviewRepo.findReviewsByUser(userUUID).toString();
    }
    
}
