package com.literario.api.model;

import lombok.Data;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@Entity
@Table(name="reviews")
public class Review {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    
    @CreationTimestamp
    private Timestamp createdAt;

    private UUID userId;
    private UUID bookId;
    private Integer rating;	
    private String review;

    @Override
    public String toString(){
        return "Review{" +
                "id=" + id +
                ", createdAt='" + createdAt + '\'' +
                ", userId=" + userId +
                ", bookId=" + bookId +
                ", rating='" + rating + '\'' +
                ", review='" + review + '\'' +
                '}';
    }

    public String getReview() {
        return review;
    }
    
    public UUID getBookId() {
        return bookId;
    }

    public UUID getUserId() {
        return userId;
    }

    public Integer getRate() {
        return rating;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public void setBookId(UUID bookId) {
        this.bookId = bookId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
