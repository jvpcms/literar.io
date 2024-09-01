package com.literario.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;	

import com.literario.api.repo.ReviewRepo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/reviews")
public class ReviewController {
    
    // private ReviewRepo reviewRepo;

    // public ReviewController(ReviewRepo reviewRepo) {
    //     this.reviewRepo = reviewRepo;
    // }

    // @PostMapping("path")
    // public String postReview(@RequestBody String entity) {
    //     reviewRepo.insertReview(entity);
    // }

    // @DeleteMapping("/{id}")
    // public void deleteReview(@PathVariable Long id) {

    //     reviewRepo.deleteReview(id);
    // }
    
    // @PutMapping("/{id}")
    // public void updateReview(@PathVariable Long id, @RequestBody String entity) {
    //     reviewRepo.updateReview(id, entity);
    // }
}
