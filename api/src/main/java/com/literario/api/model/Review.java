package com.literario.api.model;

import lombok.Data;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Data
@Entity
@Table(name="reviews")
public class Review {
    
    @Id
    private UUID id;
    private String createdAt;
    private UUID userId;
    private UUID bookId;
    private String rating;	
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
}
