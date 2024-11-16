package com.example.agoravai.services;

import com.example.agoravai.models.ReviewEntity;
import com.example.agoravai.models.ReviewRequestDTO;
import com.example.agoravai.models.LoginResponse;
import com.example.agoravai.models.NotAuthedUserEntity;
import com.example.agoravai.models.BookEntity;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.GET;

public interface LiterarioAPI {
    @POST("/reviews/post")  
    Call<ReviewEntity> createReview(@Body ReviewRequestDTO reviewRequest);
    @POST("/auth/login")
    Call<LoginResponse> loginUser(@Body NotAuthedUserEntity notAuthedUser);
    @GET("/books")
    Call<List<BookEntity>> getBooks();
}
