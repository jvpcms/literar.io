package com.literario.api.model;

import lombok.Data;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;

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

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<ReviewEntity> reviews = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "author_id", foreignKey = @ForeignKey(name = "fk_author", foreignKeyDefinition = "FOREIGN KEY (author_id) REFERENCES author(id) ON DELETE CASCADE"))
    private AuthorEntity author;

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
                ", author='" + author + '\'' +
                '}';
    }
}
