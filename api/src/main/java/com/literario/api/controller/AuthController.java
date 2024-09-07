package com.literario.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.literario.api.model.NotAuthedUserEntity;
import com.literario.api.service.AuthService;

import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody NotAuthedUserEntity notAuthedUser) {
        return AuthService.login(notAuthedUser);
    }

    @PostMapping("/validate")
    public ResponseEntity<Map<String, String>> validate(@RequestHeader String token, @RequestHeader UUID id) {
        return AuthService.verifyToken(token, id);
    }

}
