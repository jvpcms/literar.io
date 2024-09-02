package com.literario.api.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.HashMap;

@Service
public class PasswordService {

    private PasswordEncoder passwordEncoder;

    public PasswordService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Map<String, String> hashPassword(String rawPassword) {
        Map<String, String> passwordMap = new HashMap<>();

        // Gera o hash da senha
        String hash = passwordEncoder.encode(rawPassword);

        // Extrai o salt do hash gerado
        String salt = hash.substring(0, 29); // O salt é os primeiros 29 caracteres do hash

        passwordMap.put("salt", salt);
        passwordMap.put("hash", hash);

        return passwordMap;

    }

    public boolean checkPassword(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }
}

