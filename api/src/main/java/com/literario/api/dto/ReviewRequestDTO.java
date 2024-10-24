package com.literario.api.dto;

import java.util.UUID;

public class ReviewRequestDTO {
    private UUID bookId;
    private UUID userId;
    private Integer rating;
    private String review;

    // Getters and setters
    public UUID getBookId() {
        return bookId;
    }

    public void setBookId(UUID bookId) {
        this.bookId = bookId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Integer getRate() {
        return rating;
    }

    public void setRate(Integer rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}