package com.literario.api.model;

import lombok.Data;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;

import java.sql.Timestamp;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "UUID DEFAULT gen_random_uuid()", updatable = false)
    private UUID id;
    
    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP DEFAULT (now() at time zone 'utc')", nullable = false, updatable = false)
    private Timestamp createdAt;
    
    @Column(nullable = false)
    private String username;
    
    @Column(nullable = false)
    private String hash;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<ReviewEntity> reviews = new ArrayList<>();

    public UserEntity() {
    }

    public UserEntity(String username, String hash) {
        this.username = username;
        this.hash = hash;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", created_at=" + createdAt +
                ", username='" + username + '\'' +
                ", hash='" + hash + '\'' +
                '}';
    }
}
