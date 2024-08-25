package com.literario.api.model;

import lombok.Data;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Data
@Entity
@Table(name = "books")
public class Book {

    @Id 
    private String id;
    private String title;
    private String author;
    private int year;
    private String synopsis;

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", synopsis='" + synopsis + '\'' +
                '}';
    }
}
