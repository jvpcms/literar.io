package com.literario.api.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

public class CustomResponse {

    private static final String OK = "ok";
    private static final String MESSAGE = "message";
    private static final String TOKEN = "token";

    private CustomResponse() {
    }

    public static ResponseEntity<Map<String, String>> create(HttpStatus status, Boolean ok, Optional<String> message,
            Optional<String> token) {

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

    public static void copyFromResponseEntity(ResponseEntity<Map<String, String>> responseEntity, HttpServletResponse servletResponse) {
        // Set status code
        servletResponse.setStatus(responseEntity.getStatusCode().value());

        // Copy headers
        for (Map.Entry<String, java.util.List<String>> header : responseEntity.getHeaders().entrySet()) {
            for (String value : header.getValue()) {
                servletResponse.addHeader(header.getKey(), value);
            }
        }

        // Write body (if present)
        try {
            if (responseEntity.getBody() != null) {
                servletResponse.getWriter().write(responseEntity.getBody().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public static ResponseEntity<Map<String, String>> tokenNotPresent() {
        return create(HttpStatus.UNAUTHORIZED, false, Optional.of("Token not present"), Optional.empty());
    }

    public static ResponseEntity<Map<String, String>> tokenExpired() {
        return create(HttpStatus.UNAUTHORIZED, false, Optional.of("Token expired"), Optional.empty());
    }

    public static ResponseEntity<Map<String, String>> invalidSignature() {
        return create(HttpStatus.UNAUTHORIZED, false, Optional.of("Invalid signature"), Optional.empty());
    }

    public static ResponseEntity<Map<String, String>> tokenException() {
        return create(HttpStatus.FORBIDDEN, false, Optional.of("Token Exception"), Optional.empty());
    }

    public static ResponseEntity<Map<String, String>> invalidUserId() {
        return create(HttpStatus.FORBIDDEN, false, Optional.of("Invalid user id"), Optional.empty());
    }

    public static ResponseEntity<Map<String, String>> existentUser() {
        return create(HttpStatus.CONFLICT, false, Optional.of("User already exists"), Optional.empty());
    }

    public static ResponseEntity<Map<String, String>> ok() {
        return create(HttpStatus.OK, true, Optional.empty(), Optional.empty());
    }

    public static ResponseEntity<Map<String, String>> created() {
        return create(HttpStatus.CREATED, true, Optional.empty(), Optional.empty());
    }
}
