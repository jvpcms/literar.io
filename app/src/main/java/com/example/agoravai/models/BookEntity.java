package com.example.agoravai.models;

import com.google.gson.annotations.SerializedName;
import java.util.UUID;
import java.util.List;

public class BookEntity {

    @SerializedName("id")
    private UUID id;

    @SerializedName("createdAt")
    private String createdAt; // Usando String para representar o timestamp, pode ser convertido após recebimento.

    @SerializedName("title")
    private String title;

    @SerializedName("year")
    private Integer year;

    @SerializedName("synopsis")
    private String synopsis;

    @SerializedName("author")
    private AuthorEntity author;

    @SerializedName("reviews")
    private List<ReviewEntity> reviews;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public void setAuthor(AuthorEntity author) {
        this.author = author;
    }

    public List<ReviewEntity> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewEntity> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "id=" + id +
                ", createdAt='" + createdAt + '\'' +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", synopsis='" + synopsis + '\'' +
                ", author=" + author +
                ", reviews=" + reviews +
                '}';
    }

    public static class ReviewEntity {

        @SerializedName("id")
        private UUID id;

        @SerializedName("createdAt")
        private String createdAt; // Usando String para representar o timestamp, pode ser convertido após recebimento.

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
}
