package com.example.agoravai.services;

import com.example.agoravai.models.ReviewEntity;
import com.example.agoravai.models.ReviewRequestDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Interceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.UUID;

public class RetrofitService {

    private Retrofit retrofit;

    public RetrofitService() {
        initializeRetrofit();
    }

    private void initializeRetrofit() {
        // Substitua pelo token real
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJnYWJyaWVsIiwidXNlcl9pZCI6IjdiMTBhMDRlLTA5ODctNGZkZC1hNmU5LTBmMzQyOTE1ZmNmMSIsImlhdCI6MTczMTI4ODAyNiwiZXhwIjoxNzMxODkyODI2fQ.x0Cl4pKNZ7xPomWPKFK0g1xnAhdrTL7Nnt2Qv2tEwd8";

        // Interceptor para adicionar o token ao cabeçalho "authentication" sem o prefixo "Bearer "
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request newRequest = chain.request().newBuilder()
                                .addHeader("authentication", token) // Token puro
                                .build();
                        return chain.proceed(newRequest);
                    }
                })
                .connectTimeout(30, java.util.concurrent.TimeUnit.SECONDS)  // Timeout de conexão
                .readTimeout(30, java.util.concurrent.TimeUnit.SECONDS)     // Timeout de leitura
                .build();

        Gson gson = new GsonBuilder().setLenient().create(); // Definindo o gson com setLenient para maior flexibilidade

        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/") // URL base da API no emulador Android
                .client(client) // Definindo o cliente com o interceptor
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public LiterarioAPI getLiterarioAPI() {
        return retrofit.create(LiterarioAPI.class);
    }

    // Método para enviar review
    public void postReview(UUID userId, UUID bookId, Integer rate, String reviewText) {
        // Criando o objeto ReviewRequestDTO com os dados passados
        ReviewRequestDTO reviewRequest = new ReviewRequestDTO();
        reviewRequest.setUserId(userId);
        reviewRequest.setBookId(bookId);
        reviewRequest.setRate(rate);
        reviewRequest.setReview(reviewText);

        LiterarioAPI api = getLiterarioAPI();

        // Fazendo a requisição com Retrofit
        Call<ReviewEntity> call = api.createReview(reviewRequest);

        call.enqueue(new Callback<ReviewEntity>() {
            @Override
            public void onResponse(Call<ReviewEntity> call, Response<ReviewEntity> response) {
                if (response.isSuccessful()) {
                    // Se a requisição for bem-sucedida
                    System.out.println("Review enviada com sucesso!");
                } else {
                    // Caso ocorra algum erro
                    System.out.println("Erro ao enviar review. Código de resposta: " + response.code());
                    try {
                        // Se houver corpo de erro na resposta, capture o erro
                        System.out.println("Erro: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ReviewEntity> call, Throwable t) {
                // Se ocorrer alguma falha na requisição
                System.out.println("Falha na requisição: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
