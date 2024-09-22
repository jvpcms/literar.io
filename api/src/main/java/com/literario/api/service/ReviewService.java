package com.literario.api.service;

import java.util.List;
import java.util.UUID; 
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.literario.api.model.ReviewEntity;
import com.literario.api.model.UserEntity;
import com.literario.api.dto.ReviewRequestDTO;
import com.literario.api.model.BookEntity;
import com.literario.api.repo.BookRepo;
import com.literario.api.repo.UserRepo;
import com.literario.api.repo.ReviewRepo;

@Service
public class ReviewService {
    
    private ReviewRepo reviewRepo;
    private BookRepo bookRepo;
    private UserRepo userRepo;

    public ReviewService(ReviewRepo reviewRepo, BookRepo bookRepo, UserRepo userRepo){
        this.reviewRepo = reviewRepo;
        this.bookRepo = bookRepo;
        this.userRepo = userRepo;
    }

    public ResponseEntity<List<ReviewEntity>> getReviewsByUser(UUID userId){
        Optional<UserEntity> user = userRepo.findUserById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reviewRepo.findReviewsByUser(user.get()));
    }

    public ResponseEntity<List<ReviewEntity>> getReviewsByBook(UUID bookId){
        Optional<BookEntity> book = bookRepo.findBookById(bookId);
        if (book.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reviewRepo.findReviewsByBook(book.get()));
    }

    public ResponseEntity<ReviewEntity> postReview(ReviewRequestDTO reviewRequest){
        int rating = reviewRequest.getRate();
        String review = reviewRequest.getReview();
        Optional<BookEntity> book = bookRepo.findBookById(reviewRequest.getBookId());
        Optional<UserEntity> user = userRepo.findUserById(reviewRequest.getUserId());
        if (user.isEmpty() || book.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reviewRepo.insertReview(user.get(), book.get(), rating, review));
    }

    public ResponseEntity<String> deleteReview(UUID reviewId){
        Optional<ReviewEntity> review = reviewRepo.findReviewById(reviewId);
        if (review.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        reviewRepo.deleteReview(reviewId);
        return ResponseEntity.status(204).body("Review deleted");
    }

    public ResponseEntity<ReviewEntity> updateReview(UUID reviewId, ReviewRequestDTO reviewRequest){
        Optional<ReviewEntity> review = reviewRepo.findReviewById(reviewId);
        if (review.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reviewRepo.updateReview(review.get(), reviewRequest.getRate()));
    }
}
