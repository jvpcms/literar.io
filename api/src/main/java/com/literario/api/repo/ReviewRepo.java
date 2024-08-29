package com.literario.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

import com.literario.api.model.Review;
import com.literario.api.model.Users;
import com.literario.api.model.Book;

import jakarta.transaction.Transactional;

@RepositoryRestResource
public interface ReviewRepo extends JpaRepository<Review,String> {
    
    @Query("SELECT re.id, re.created_at, us.username, bo.title, re.rating, re.review " +
    "FROM Review re " +
    "LEFT JOIN Users us ON re.user_id = us.id " +
    "LEFT JOIN Books bo ON bo.id = re.book_id " +
    "WHERE re.book_id = :book_id")
    List<Review> findReviewsByBook(@Param("book_id") String book_id);

    @Query("SELECT re.id, re.created_at, us.username, bo.title, re.rating, re.review " +
    "FROM Review re " +
    "LEFT JOIN Users us ON re.user_id = us.id " +
    "LEFT JOIN Book bo ON re.book_id = bo.id " +
    "WHERE re.user_id = :user_id")
    List<Review> findReviewsByUser(@Param("user_id") String user_id);

    @Transactional
    @Modifying
    @Query("INSERT INTO Review (user_id, book_id, rating, review) VALUES (:user_id, :book_id, :rating, :review)")
    void insertReview(@Param("user_id") String user_id, @Param("book_id") String book_id, @Param("rating") String rating, @Param("review") String review);

    @Transactional
    @Modifying
    @Query("UPDATE Review SET rating = :rating WHERE user_id = :user_id AND book_id = :book_id")
    void updateReview(@Param("user_id") String user_id, @Param("book_id") String book_id, @Param("rating") String rating);

    @Transactional
    @Modifying
    @Query("DELETE FROM Review WHERE user_id = :user_id AND book_id = :book_id")
    void deleteReview(@Param("user_id") String user_id, @Param("book_id") String book_id);
}

// @Query("SELECT re.id, re.created_at, us.username, bo.title, re.rating, re.review FROM Review re LEFT JOIN Users us ON re.user_id = us.id LEFT JOIN Books bo ON bo.id = re.book_id WHERE re.book_id = :book_id")
// List<Review> findReviewsbyBook(@Param ("book_id") String book_id);