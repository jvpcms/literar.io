package com.literario.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.literario.api.model.BookEntity;
import com.literario.api.model.ReviewEntity;
import com.literario.api.model.UserEntity;

@RepositoryRestResource
public interface ReviewRepo extends JpaRepository<ReviewEntity, UUID> {

  @Query("SELECT re FROM ReviewEntity re WHERE re.book = :book")
  List<ReviewEntity> findReviewsByBook(@Param("book") BookEntity book);

  @Query("SELECT re FROM ReviewEntity re WHERE re.user = :user")
  List<ReviewEntity> findReviewsByUser(@Param("user") UserEntity userId);

  // return las 2 reviews for posted across all books
  @Query("SELECT re FROM ReviewEntity re ORDER BY re.createdAt DESC")
  List<ReviewEntity> findLatestReviews(Pageable pageable);

  default ReviewEntity insertReview(UserEntity user, BookEntity book, Integer rating, String review) {
    ReviewEntity newReview = new ReviewEntity();
    newReview.setUser(user);
    newReview.setBook(book);
    newReview.setRating(rating);
    newReview.setReview(review);
    return save(newReview);
  }

  default ReviewEntity updateReview(ReviewEntity review, Integer rating) {
    review.setRating(rating);
    save(review);
    return review;
  }

  default void deleteReview(UUID reviewId) {
    deleteById(reviewId);
  }

  default Optional<ReviewEntity> findReviewById(UUID reviewId) {
    return findById(reviewId);
  }
}
