package com.literario.api.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.literario.api.model.Book;
import com.literario.api.repo.BookRepo;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepo bookRepo;

    public BookController(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @PostMapping("/post")
    public ResponseEntity<Book> postBook(@RequestBody Book bookRequest) {
        try {
            Book savedBook = bookRepo.save(bookRequest);
            return ResponseEntity.ok(savedBook);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") UUID bookId) {
        try {
            if (bookRepo.existsById(bookId)) {
                bookRepo.deleteById(bookId);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Book> updateBook(@PathVariable("id") UUID bookId, @RequestBody Book bookRequest) {
        try {
            if (bookRepo.existsById(bookId)) {
                bookRequest.setId(bookId);
                Book updatedBook = bookRepo.save(bookRequest);
                return ResponseEntity.ok(updatedBook);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
}
