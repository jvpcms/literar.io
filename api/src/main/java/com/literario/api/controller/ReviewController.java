package com.literario.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;	

import com.literario.api.repo.ReviewRepo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.literario.api.model.ReviewEntity; // Add this import statement

import java.util.UUID;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    
    private ReviewRepo reviewRepo;

    public ReviewController(ReviewRepo reviewRepo) {
        this.reviewRepo = reviewRepo;
        }

    @PostMapping("/post")
    public void postReview(@RequestBody ReviewEntity reviewRequest) {
        UUID bookId = reviewRequest.getBookId();
        UUID userId = reviewRequest.getUserId();
        String review = reviewRequest.getReview();
        Integer rate = reviewRequest.getRate();
        
        try{
            reviewRepo.insertReview(userId, bookId, rate, review);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/{id}/delete")
    public void deleteReview(@PathVariable("id") UUID reviewId) { 
        try {
            reviewRepo.deleteReview(reviewId);
        } catch (Exception e) {
            e.printStackTrace();
        }    
    }
    
    @PutMapping("/{id}/update")
    public void updateReview(@PathVariable("id") UUID reviewId, @RequestBody ReviewEntity reviewRequest) {
        try {
            reviewRepo.updateReview(reviewId, reviewRequest.getRate());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
