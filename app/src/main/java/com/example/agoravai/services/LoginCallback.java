package com.example.agoravai.services;

public interface LoginCallback {
    void onSuccess(String token);   // Método chamado em caso de sucesso
    void onFailure(String error);   // Método chamado em caso de falha
}
