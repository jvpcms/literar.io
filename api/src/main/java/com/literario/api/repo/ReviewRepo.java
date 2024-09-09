package com.literario.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

import com.literario.api.model.Review;

@RepositoryRestResource
public interface ReviewRepo extends JpaRepository<Review,UUID> {
    
    @Query("SELECT re FROM Review re WHERE re.bookId = :bookId")
    List<Review> findReviewsByBook(@Param("bookId") UUID bookId);
    
    @Query("SELECT re FROM Review re WHERE re.userId = :userId")
    List<Review> findReviewsByUser(@Param("userId") UUID userId);

    default void insertReview(UUID userId, UUID bookId, int rating, String review){
        Review newReview = new Review();
        newReview.setUserId(userId);
        newReview.setBookId(bookId);
        newReview.setRating(rating);
        newReview.setReview(review);
        save(newReview);
    }

    default void updateReview(UUID reviewId, Integer rating) throws Exception{
        Review review = findById(reviewId).get();
        if (review == null) {
            throw new Exception("Review not found");
        }
        review.setRating(rating);
        save(review);
    }

    default void deleteReview(UUID reviewId) throws Exception{
        Review review = findById(reviewId).get();
        if (review == null) {
            throw new Exception("Review not found");
        }
        deleteById(reviewId);
    }
}