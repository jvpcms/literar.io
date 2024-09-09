package com.literario.api.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.literario.api.model.UserEntity;
import com.literario.api.model.NotAuthedUserEntity;
import com.literario.api.repo.UserRepo;
import com.literario.api.utils.CustomResponse;

@Service
public class UserService {
    
    private UserRepo userRepo;
    private PasswordService passwordService;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
        this.passwordService = new PasswordService();
    }

    public ResponseEntity<Map<String, String>> registerUser(NotAuthedUserEntity notAuthedUser) {

        //check if user already exists
        List<UserEntity> existentUsers = userRepo.findByUsername(notAuthedUser.getUsername());
        if (!existentUsers.isEmpty()) {
            return CustomResponse.existentUser();
        }

        // hash password
        String hashedPassword = passwordService.hashPassword(notAuthedUser.getPassword());
        UserEntity user = new UserEntity(notAuthedUser.getUsername(), hashedPassword);

        userRepo.save(user);
        return CustomResponse.created();
    }
}
