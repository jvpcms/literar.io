package com.literario.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;	

import com.literario.api.repo.ReviewRepo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.literario.api.model.Review; // Add this import statement

import java.util.UUID;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    
    private ReviewRepo reviewRepo;

    public ReviewController(ReviewRepo reviewRepo) {
        this.reviewRepo = reviewRepo;
        }

    @PostMapping("/post")
    public void postReview(@RequestBody Review review_request) {
        UUID bookId = review_request.getBookId();
        UUID userId = review_request.getUserId();
        String review = review_request.getReview();
        float rate = review_request.getRate();
        
        reviewRepo.insertReview(userId, bookId, rate, review);
    }

    // @DeleteMapping("/{id}/delete")
    // public void deleteReview(@PathVariable Long reviewid) {
    // reviewRepo.deleteReview(reviewid);
    // }
    
    // @PutMapping("/{id}")
    // public void updateReview(@PathVariable Long id, @RequestBody String entity) {
    //     reviewRepo.updateReview(id, entity);
    // }
}
