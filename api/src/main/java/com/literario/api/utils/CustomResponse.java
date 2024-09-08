package com.literario.api.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

public class CustomResponse {

    private static final String OK = "ok";
    private static final String MESSAGE = "message";
    private static final String TOKEN = "token";

    private CustomResponse() {}

    public static ResponseEntity<Map<String, String>> create(HttpStatus status, Boolean ok, Optional<String> message, Optional<String> token) {

        Map<String, String> responseBody = new HashMap<>();

        responseBody.put(OK, ok.toString());

        if (message.isPresent()) {
            responseBody.put(MESSAGE, message.get());
        }
        if (token.isPresent()) {
            responseBody.put(TOKEN, token.get());
        }

        return ResponseEntity.status(status).body(responseBody);
    }

    public static ResponseEntity<Map<String, String>> userNotFound() {
        return create(HttpStatus.NOT_FOUND, false, Optional.of("User not found"), Optional.empty());
    }

    public static ResponseEntity<Map<String, String>> invalidPassword() {
        return create(HttpStatus.UNAUTHORIZED, false, Optional.of("Invalid password"), Optional.empty());
    }

    public static ResponseEntity<Map<String, String>> returnToken(String token) {
        return create(HttpStatus.OK, true, Optional.empty(), Optional.of(token));
    }

    public static ResponseEntity<Map<String, String>> tokenExpired() {
        return create(HttpStatus.UNAUTHORIZED, false, Optional.of("Token expired"), Optional.empty());
    }

    public static ResponseEntity<Map<String, String>> invalidSignature() {
        return create(HttpStatus.UNAUTHORIZED, false, Optional.of("Invalid signature"), Optional.empty());
    }

    public static ResponseEntity<Map<String, String>> invalidUserId() {
        return create(HttpStatus.FORBIDDEN, false, Optional.of("Invalid user id"), Optional.empty());
    }

    public static ResponseEntity<Map<String, String>> ok() {
        return create(HttpStatus.OK, true, Optional.empty(), Optional.empty());
    }
}
