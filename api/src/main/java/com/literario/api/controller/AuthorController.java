package com.literario.api.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.literario.api.repo.AuthorRepo;
import com.literario.api.repo.BookRepo;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private AuthorRepo authorRepo;

    @Autowired
    private BookRepo bookRepo;
    
    public AuthorController() {
    }
    public AuthorController(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    @GetMapping
    public String getAuthors(){
        return authorRepo.findAll().toString();
    }

    @GetMapping("/{id}/books")
    public String getBooksByAuthor(@PathVariable UUID id){
        return bookRepo.findBooksByAuthor(id).toString();
    }

}