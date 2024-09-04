package com.literario.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;
import java.util.UUID;

import com.literario.api.model.ReviewEntity;

import jakarta.transaction.Transactional;

@RepositoryRestResource
public interface ReviewRepo extends JpaRepository<ReviewEntity,UUID> {
    
    @Query("SELECT re FROM ReviewEntity re WHERE re.book = :bookId")
    List<ReviewEntity> findReviewsByBook(@Param("bookId") UUID bookId);
    
    @Query("SELECT re FROM ReviewEntity re WHERE re.user = :userId")
    List<ReviewEntity> findReviewsByUser(@Param("userId") UUID userId);

    @Transactional
    @Modifying
    @Query("INSERT INTO ReviewEntity (user, book, rating, review) VALUES (:userId, :bookId, :rating, :review)")
    void insertReview(@Param("userId") UUID userId, @Param("bookId") UUID bookId, @Param("rating") Integer rating, @Param("review") String review);

    @Transactional
    @Modifying
    @Query("UPDATE ReviewEntity SET rating = :rating WHERE id = :reviewId")
    void updateReview(@Param("reviewId") UUID reviewId, @Param("rating") Integer rating);

    @Transactional
    @Modifying
    @Query("DELETE FROM ReviewEntity WHERE id = :reviewId")
    void deleteReview(@Param("reviewId") UUID reviewId);
}