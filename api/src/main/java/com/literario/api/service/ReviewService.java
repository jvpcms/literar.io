package com.literario.api.service;

import java.util.List;
import java.util.UUID; // Add this import statement
import java.util.ArrayList; // Add this import statement

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.literario.api.model.Review; // Add this import statement
import com.literario.api.repo.ReviewRepo;

@Service
public class ReviewService {
    
    private ReviewRepo reviewRepo;

    public ReviewService(ReviewRepo reviewRepo) {
        this.reviewRepo = reviewRepo;
    }

    public List<Review> getReviewsByUser(UUID userId) {
        try {
            return reviewRepo.findReviewsByUser(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Review> getReviewsByBook(UUID bookId) {
        try {
            return reviewRepo.findReviewsByBook(bookId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public ResponseEntity<Review> postReview(Review reviewRequest) {
        try {
            UUID bookId = reviewRequest.getBookId();
            UUID userId = reviewRequest.getUserId();
            int rating = reviewRequest.getRate();
            String review = reviewRequest.getReview();

            reviewRepo.insertReview(userId, bookId, rating, review);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<Review> deleteReview(UUID reviewId) {
        try {
            reviewRepo.deleteReview(reviewId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<Review> updateReview(UUID reviewId, Review reviewRequest) {
        try {
            reviewRepo.updateReview(reviewId, reviewRequest.getRate());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }
}
