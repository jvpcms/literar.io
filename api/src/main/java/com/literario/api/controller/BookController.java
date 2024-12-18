package com.literario.api.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;

import java.util.UUID;
import java.util.List;
import java.util.Optional;

import com.literario.api.repo.BookRepo;
import com.literario.api.repo.ReviewRepo;
import com.literario.api.repo.UserRepo;
import com.literario.api.service.ReviewService;
import com.literario.api.model.BookEntity;
import com.literario.api.model.ReviewEntity;
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
    public ResponseEntity<List<BookEntity>> getBooks() {
        return ResponseEntity.ok(bookRepo.findAll());
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<BookEntity> getBookById(@PathVariable("id") UUID bookId) {
        Optional<BookEntity> book = bookRepo.findBookById(bookId);
        if (book.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book.get());
    }

    @GetMapping("/{year}")
    public String getBooksByYear(@PathVariable Integer year) {
        return bookRepo.findBooksByYear(year).toString();
    }

    // @GetMapping("/info/{id}")
    // public ResponseEntity<BookEntity> getBookById(@PathVariable("id") UUID bookId) {
    //     Optional<BookEntity> book = bookRepo.findBookById(bookId);
    //     if (book.isEmpty()) {
    //         return ResponseEntity.notFound().build();
    //     }
    //     return ResponseEntity.ok(book.get());
    // }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<List<ReviewEntity>> getReviews(@PathVariable("id") UUID bookId) {
        return reviewService.getReviewsByBook(bookId);
    }
}
