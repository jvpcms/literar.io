package com.literario.api.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

import com.literario.api.repo.BookRepo;
import com.literario.api.repo.ReviewRepo;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookRepo bookRepo;
    private ReviewRepo reviewRepo;

    public BookController(BookRepo bookRepo, ReviewRepo reviewRepo) {
        this.bookRepo = bookRepo;
        this.reviewRepo = reviewRepo;
    }

    @GetMapping
    public String getBooks() {
        return bookRepo.findAll().toString();
    }

    @GetMapping("/{year}")
    public String getBooksByYear(@PathVariable Integer year) {
        return bookRepo.findBooksByYear(year).toString();
    }

    @GetMapping("/{id}/reviews")
    public String getReviewsByBook(@PathVariable UUID id) {
        return reviewRepo.findReviewsByBook(id).toString();
    }

}
