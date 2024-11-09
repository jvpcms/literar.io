package com.example.agoravai.models;

import com.google.gson.annotations.SerializedName;
import java.util.UUID;

public class ReviewEntity {

    @SerializedName("id")
    private UUID id;

    @SerializedName("createdAt")
    private String createdAt;  // Usando String para representar o timestamp, pode ser convertido após recebimento.

    @SerializedName("review")
    private String review;

    @SerializedName("rating")
    private Integer rating;

    @SerializedName("user")
    private UserEntity user;

    @SerializedName("book")
    private BookEntity book;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public UUID getBookId() {
        return book != null ? book.getId() : null;
    }

    public String getUserId() {
        return user != null ? user.getId() : null;
    }

    @Override
    public String toString() {
        return "ReviewEntity{" +
                "id=" + id +
                ", createdAt='" + createdAt + '\'' +
                ", review='" + review + '\'' +
                ", rating=" + rating +
                ", user=" + user +
                ", book=" + book +
                '}';
    }
}
