package com.literario.api.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;

import java.util.List;

import com.literario.api.model.UserEntity;
import com.literario.api.model.NotAuthedUserEntity;
import com.literario.api.repo.UserRepo;

@Service
public class UserService {
    
    private UserRepo userRepo;
    private PasswordService passwordService;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
        this.passwordService = new PasswordService();
    }

    public ResponseEntity<UserEntity> registerUser(NotAuthedUserEntity notAuthedUser) {

        //check if user already exists
        List<UserEntity> existentUsers = userRepo.findByUsername(notAuthedUser.getUsername());
        if (!existentUsers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        // hash password
        String hashedPassword = passwordService.hashPassword(notAuthedUser.getPassword());
        UserEntity user = new UserEntity(notAuthedUser.getUsername(), hashedPassword);

        UserEntity savedUser = userRepo.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
}
