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
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "UUID DEFAULT gen_random_uuid()", updatable = false)
    private UUID id;

    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP DEFAULT (now() at time zone 'utc')", nullable = false, updatable = false)
    private Timestamp createdAt;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "author_id", foreignKey = @ForeignKey(name = "fk_author", foreignKeyDefinition = "FOREIGN KEY (author_id) REFERENCES author(id) ON DELETE CASCADE"))
    private Author author;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private String synopsis;

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author.getName() + '\'' + // Para exibir o nome do autor
                ", year=" + year +
                ", synopsis='" + synopsis + '\'' +
                '}';
    }
}