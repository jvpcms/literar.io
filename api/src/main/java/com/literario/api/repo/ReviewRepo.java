package com.literario.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

import com.literario.api.model.BookEntity;
import com.literario.api.model.ReviewEntity;
import com.literario.api.model.UserEntity;

@RepositoryRestResource
public interface ReviewRepo extends JpaRepository<ReviewEntity,UUID> {
    
    @Query("SELECT re FROM ReviewEntity re WHERE re.book = :bookId")
    List<ReviewEntity> findReviewsByBook(@Param("bookId") UUID bookId);
    
    @Query("SELECT re FROM ReviewEntity re WHERE re.user = :userId")
    List<ReviewEntity> findReviewsByUser(@Param("userId") UUID userId);

    default void insertReview(UserEntity user, BookEntity book, Integer rating, String review){
        ReviewEntity newReview = new ReviewEntity();
        newReview.setUser(user);
        newReview.setBook(book);
        newReview.setRating(rating);
        newReview.setReview(review);
        save(newReview);
    }

    default void updateReview(UUID reviewId, Integer rating) throws Exception{
        ReviewEntity review = findById(reviewId).get();
        if (review == null) {
            throw new Exception("Review not found");
        }
        review.setRating(rating);
        save(review);
    }

    default void deleteReview(UUID reviewId) throws Exception{
        ReviewEntity review = findById(reviewId).get();
        if (review == null) {
            throw new Exception("Review not found");
        }
        deleteById(reviewId);
    }
}