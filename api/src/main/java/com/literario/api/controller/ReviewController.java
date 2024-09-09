package com.literario.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;	

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.literario.api.model.Review; // Add this import statement
import com.literario.api.service.ReviewService; // Add this import statement

import java.util.UUID;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/post")
    public void postReview(@RequestBody Review reviewRequest) {
        reviewService.postReview(reviewRequest);
    }

    @DeleteMapping("/{id}/delete")
    public void deleteReview(@PathVariable("id") UUID reviewId) { 
        reviewService.deleteReview(reviewId);
    }
    
    @PutMapping("/{id}/update")
    public void updateReview(@PathVariable("id") UUID reviewId, @RequestBody Review reviewRequest) {
        reviewService.updateReview(reviewId, reviewRequest);
    }
}
