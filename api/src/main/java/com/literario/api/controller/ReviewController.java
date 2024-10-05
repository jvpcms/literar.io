package com.literario.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.literario.api.dto.ReviewRequestDTO;
import com.literario.api.model.ReviewEntity;
import com.literario.api.service.ReviewService;


import java.util.UUID;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/post")
    public ResponseEntity<ReviewEntity> postReview(@RequestBody ReviewRequestDTO reviewRequest){
       return reviewService.postReview(reviewRequest);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteReview(@PathVariable("id") UUID reviewId) { 
        return reviewService.deleteReview(reviewId);
    }
    
    @PutMapping("/{id}/update")
    public ResponseEntity<ReviewEntity> updateReview(@PathVariable("id") UUID reviewId, @RequestBody ReviewRequestDTO reviewRequest) {
        return reviewService.updateReview(reviewId, reviewRequest);
    }
}
