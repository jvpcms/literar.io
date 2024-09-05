package com.literario.api.service;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.sql.Timestamp;

import com.literario.api.model.UserEntity;
import com.literario.api.repo.UserRepo;

@Service
public class UserService {
    
    private UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public ResponseEntity<UserEntity> registerUser(UserEntity user) {

        //check if user already exists
        List<UserEntity> users = userRepo.findByUsername(user.getUsername());
        if (!users.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        user.setId(UUID.randomUUID());
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        UserEntity savedUser = userRepo.save(user);
        return ResponseEntity.ok(savedUser);
    }
}
