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

import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;


@Data
@Entity
@Table(name = "books")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "UUID DEFAULT gen_random_uuid()", updatable = false)
    private UUID id;
    
    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP DEFAULT (now() at time zone 'utc')", nullable = false, updatable = false)
    private Timestamp createdAt;
    
    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false)
    private Integer year;
    
    @Column(nullable = false)
    private String synopsis;
    
    @Column(nullable = false)
    private UUID authorId;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<ReviewEntity> reviews = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", synopsis='" + synopsis + '\'' +
                ", author='" + authorId + '\'' +
                '}';
    }
}
