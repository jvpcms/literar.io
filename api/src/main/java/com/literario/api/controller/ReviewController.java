package com.literario.api.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.literario.api.model.Review;
import com.literario.api.repo.ReviewRepo;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewRepo reviewRepo;

    public ReviewController(ReviewRepo reviewRepo) {
        this.reviewRepo = reviewRepo;
    }

    @PostMapping("/post")
    public ResponseEntity<Review> postReview(@RequestBody Review reviewRequest) {
        try {
            Review savedReview = reviewRepo.save(reviewRequest);
            return ResponseEntity.ok(savedReview);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteReview(@PathVariable("id") UUID reviewId) {
        try {
            if (reviewRepo.existsById(reviewId)) {
                reviewRepo.deleteById(reviewId);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Review> updateReview(@PathVariable("id") UUID reviewId, @RequestBody Review reviewRequest) {
        try {
            if (reviewRepo.existsById(reviewId)) {
                reviewRequest.setId(reviewId);
                Review updatedReview = reviewRepo.save(reviewRequest);
                return ResponseEntity.ok(updatedReview);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
}
