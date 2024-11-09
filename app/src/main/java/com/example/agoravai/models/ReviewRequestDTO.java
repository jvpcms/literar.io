package com.example.agoravai.models;

import java.util.UUID;

public class ReviewRequestDTO {
    private UUID bookId;
    private UUID userId;
    private Integer rate;
    private String review;

    // Getters e Setters
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
        return rate;  // Alterei de rating para rate
    }

    public void setRate(Integer rate) {
        this.rate = rate;  // Alterei de rating para rate
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
