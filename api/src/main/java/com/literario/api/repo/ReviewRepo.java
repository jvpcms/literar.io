package com.literario.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;
import java.util.UUID;

import com.literario.api.model.Review;

import jakarta.transaction.Transactional;

@RepositoryRestResource
public interface ReviewRepo extends JpaRepository<Review,UUID> {
    
    @Query("SELECT re FROM Review re WHERE re.bookId = :bookId")
    List<Review> findReviewsByBook(@Param("bookId") UUID bookId);
    
    @Query("SELECT re FROM Review re WHERE re.userId = :userId")
    List<Review> findReviewsByUser(@Param("userId") UUID userId);

    @Transactional
    @Modifying
    @Query("INSERT INTO Review (userId, bookId, rating, review) VALUES (:userId, :bookId, :rating, :review)")
    void insertReview(@Param("userId") UUID userId, @Param("bookId") UUID bookId, @Param("rating") float rating, @Param("review") String review);

    @Transactional
    @Modifying
    @Query("UPDATE Review SET rating = :rating WHERE id = :reviewId")
    void updateReview(@Param("reviewId") UUID reviewId, @Param("rating") Integer rating);

    @Transactional
    @Modifying
    @Query("DELETE FROM Review WHERE id = :reviewId")
    void deleteReview(@Param("reviewId") UUID reviewId);
}