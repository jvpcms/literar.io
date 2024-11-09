package com.example.agoravai.services;

import com.example.agoravai.models.ReviewEntity;
import com.example.agoravai.models.ReviewRequestDTO;  // Importando o modelo ReviewRequestDTO
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LiterarioAPI {
    // Método POST para enviar uma nova review
    @POST("/reviews/post")  // URL correspondente ao endpoint da sua API
    Call<ReviewEntity> createReview(@Body ReviewRequestDTO reviewRequest);
}
