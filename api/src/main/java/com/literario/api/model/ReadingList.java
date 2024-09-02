package com.literario.api.model;

import lombok.Data;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "reading_lists")
public class ReadingList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Override
    public String toString() {
        return "ReadingList{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", user=" + user +
                ", book=" + book +
                '}';
    }
}
