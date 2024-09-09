package com.literario.api.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

import com.literario.api.repo.BookRepo;
import com.literario.api.repo.ReviewRepo;
import com.literario.api.repo.UserRepo;
import com.literario.api.service.ReviewService;
@RestController
@RequestMapping("/books")
public class BookController {

    private BookRepo bookRepo;
    private ReviewService reviewService;

    public BookController(BookRepo bookRepo, ReviewRepo reviewRepo, UserRepo userRepo) {
        this.bookRepo = bookRepo;
        this.reviewService = new ReviewService(reviewRepo, bookRepo, userRepo);
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
    public String getReviews(@PathVariable("id") UUID bookId) {
        return reviewService.getReviewsByBook(bookId).toString();
    }

}
