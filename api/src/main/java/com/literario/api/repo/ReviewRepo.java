package com.literario.api.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.literario.api.model.Review;

@RepositoryRestResource
public interface ReviewRepo extends JpaRepository<Review, UUID> {

    @Query("SELECT r FROM Review r WHERE r.bookId = :bookId")
    List<Review> findReviewsByBook(@Param("bookId") UUID bookId);

    @Query("SELECT r FROM Review r WHERE r.userId = :userId")
    List<Review> findReviewsByUser(@Param("userId") UUID userId);
}
