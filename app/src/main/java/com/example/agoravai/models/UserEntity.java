package com.example.agoravai.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class UserEntity {

    @SerializedName("id")
    private String id; // Usando String porque o UUID no backend será convertido para String no JSON

    @SerializedName("createdAt")
    private String createdAt; // Para Timestamp, você pode usar String e depois converter no app Android

    @SerializedName("username")
    private String username;

    @SerializedName("hash")
    private String hash;

    @SerializedName("reviews")
    private List<BookEntity.ReviewEntity> reviews; // Dependendo da resposta, você precisará de uma classe ReviewEntity também

    // Construtores
    public UserEntity() {
    }

    public UserEntity(String id, String createdAt, String username, String hash, List<BookEntity.ReviewEntity> reviews) {
        this.id = id;
        this.createdAt = createdAt;
        this.username = username;
        this.hash = hash;
        this.reviews = reviews;
    }

    // Getters e setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public List<BookEntity.ReviewEntity> getReviews() {
        return reviews;
    }

    public void setReviews(List<BookEntity.ReviewEntity> reviews) {
        this.reviews = reviews;
    }
}
