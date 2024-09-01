package com.literario.api.service;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.sql.Timestamp;

import com.literario.api.model.User;
import com.literario.api.repo.UserRepo;

@Service
public class UserService {
    
    private UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public ResponseEntity<User> registerUser(User user) {

        //check if user already exists
        List<User> users = userRepo.findByUsername(user.getUsername());
        if (!users.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        user.setId(UUID.randomUUID());
        user.setCreated_at(new Timestamp(System.currentTimeMillis()));

        User savedUser = userRepo.save(user);
        return ResponseEntity.ok(savedUser);
    }
}
