package com.literario.api.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;

import com.literario.api.repo.BookRepo;
import com.literario.api.repo.AuthorRepo;
import com.literario.api.model.BookEntity;
import com.literario.api.model.AuthorEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/authors")
public class AuthorController {

    private BookRepo bookRepo;
    private AuthorRepo authorRepo;

    public AuthorController(BookRepo bookRepo, AuthorRepo authorRepo) {
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorEntity> getAuthorById(@PathVariable("id") UUID authorId) {
        Optional<AuthorEntity> author = authorRepo.findAuthorById(authorId);
        if (author.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(author.get());
    }

    @GetMapping("/{id}/books")
    public ResponseEntity<List<BookEntity>> getBooksByAuthor(@PathVariable("id") UUID authorId) {
        Optional<AuthorEntity> author = authorRepo.findAuthorById(authorId);
        if (author.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bookRepo.findBooksByAuthor(author.get()));
    }
}
