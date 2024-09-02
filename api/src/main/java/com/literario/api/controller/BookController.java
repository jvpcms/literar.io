package com.literario.api.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.literario.api.model.Review;
import com.literario.api.repo.BookRepo;

import java.util.UUID;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookRepo bookRepo;

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

<<<<<<< HEAD
=======
    // @GetMapping("/{id}/reviews")
    // public String getReviewsByBook(@PathVariable("id") UUID bookId) {
    //     return reviewRepo.findReviewsByBook(bookId).toString();
    // }
    @GetMapping("/{id}/reviews")
    public String getReviewsByBook(@PathVariable("id") UUID bookId) {
        try {
            return reviewRepo.findReviewsByBook(bookId).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
>>>>>>> reviews_endpoints
}
