package com.literario.api.model;

import lombok.Data;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="reviews")
public class Review {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private LocalDateTime createdAt;
    private UUID userId;
    private UUID bookId;
    private float rating;	
    private String review;
    private String title;
    private String username;

    @Override
    public String toString(){
        return "Review{" +
                "id=" + id +
                ", createdAt='" + createdAt + '\'' +
                ", userId=" + userId +
                ", bookId=" + bookId +
                ", rating='" + rating + '\'' +
                ", review='" + review + '\'' +
                ", title='" + title + '\'' +
                ", username='" + username + '\'' +
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

    public float getRate() {
        return rating;
    }
}
