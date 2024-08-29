package com.literario.api.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.literario.api.repo.ReviewRepo;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/users")
public class UsersController {
    
    private ReviewRepo reviewRepo;

    @GetMapping("/{id}/reviews")
    public String getReviewsByUser(@PathVariable String id) {
        return reviewRepo.findReviewsByUser(id).toString();
    }
    
}
