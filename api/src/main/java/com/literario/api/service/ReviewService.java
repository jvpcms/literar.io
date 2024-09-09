package com.literario.api.service;

import java.util.List;
import java.util.UUID; // Add this import statement
import java.util.ArrayList; // Add this import statement

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.literario.api.model.ReviewEntity;
import com.literario.api.model.UserEntity;
import com.literario.api.model.BookEntity;
import com.literario.api.repo.BookRepo;
import com.literario.api.repo.UserRepo;
import com.literario.api.repo.ReviewRepo;

@Service
public class ReviewService {
    
    private ReviewRepo reviewRepo;
    private BookRepo bookRepo;
    private UserRepo userRepo;

    public ReviewService(ReviewRepo reviewRepo, BookRepo bookRepo, UserRepo userRepo) {
        this.reviewRepo = reviewRepo;
        this.bookRepo = bookRepo;
        this.userRepo = userRepo;
    }

    public List<ReviewEntity> getReviewsByUser(UUID userId) {
        try {
            return reviewRepo.findReviewsByUser(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<ReviewEntity> getReviewsByBook(UUID bookId) {
        try {
            return reviewRepo.findReviewsByBook(bookId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public ResponseEntity<ReviewEntity> postReview(ReviewEntity reviewRequest) {
        try {
            BookEntity book = bookRepo.findBookById(reviewRequest.getBookId());
            UserEntity user = userRepo.findUserById(reviewRequest.getUserId());
            int rating = reviewRequest.getRate();
            String review = reviewRequest.getReview();

            reviewRepo.insertReview(user, book, rating, review);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<ReviewEntity> deleteReview(UUID reviewId) {
        try {
            reviewRepo.deleteReview(reviewId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<ReviewEntity> updateReview(UUID reviewId, ReviewEntity reviewRequest) {
        try {
            reviewRepo.updateReview(reviewId, reviewRequest.getRate());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }
}
