package com.literario.api.model;

import lombok.Data;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ForeignKey;

import java.util.UUID;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
@Table(name="reviews")
public class ReviewEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "UUID DEFAULT gen_random_uuid()", updatable = false)
    private UUID id;
    
    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP DEFAULT (now() at time zone 'utc')", nullable = false, updatable = false)
    private Timestamp createdAt;
    
    @Column(nullable = false)
    private String review;
    
    @Column(nullable = false)
    private Integer rating;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "user_id", foreignKey = @ForeignKey(name = "fk_user_review", foreignKeyDefinition = "FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE"))
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "book_id", foreignKey = @ForeignKey(name = "fk_book_review", foreignKeyDefinition = "FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE"))
    private BookEntity book;
    
    public UUID getBookId() {
        return book.getId();
    }

    public UUID getUserId() {
        return user.getId();
    }

    public Integer getRate() {
        return rating;
    }

    public String getReview() {
        return review;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void setReview(String review) {
        this.review = review;
    }
    
    @Override
    public String toString(){
        return "Review{" +
                "id=" + id +
                ", createdAt='" + createdAt + '\'' +
                ", userId=" + user.getId() +
                ", bookId=" + book.getId() +
                ", title='" + review + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
    
}
