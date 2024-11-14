package com.example.agoravai.services;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.agoravai.activities.BookActivity;
import com.example.agoravai.models.BookEntity;
import com.example.agoravai.models.ReviewEntity;
import com.example.agoravai.models.ReviewRequestDTO;
import com.example.agoravai.models.NotAuthedUserEntity;
import com.example.agoravai.models.LoginResponse;
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
import java.util.List;
import java.util.UUID;

public class RetrofitService {

    private Retrofit retrofit;
    private Context context;

    public RetrofitService(Context context) {
        this.context = context;
        initializeRetrofit(context); // Passa o contexto para obter o token armazenado
    }

    private void initializeRetrofit(Context context) {
        // Obtém o token armazenado no SharedPreferences
        SharedPreferences sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        final String token = sharedPreferences.getString("auth_token", ""); // Recupera o token salvo

        // Verifica se o token existe, caso contrário, não faz a requisição com token
        if (token.isEmpty()) {
            // Caso o token esteja vazio, você pode adicionar lógica para tratar isso
            Log.e("RetrofitService", "Token de autenticação não encontrado");
        }

        // Interceptor para adicionar o token ao cabeçalho "authentication"
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request newRequest = chain.request().newBuilder()
                                .addHeader("authentication", token) // Token obtido dos SharedPreferences
                                .build();
                        return chain.proceed(newRequest);
                    }
                })
                .connectTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
                .readTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
                .build();

        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/") // URL base
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

    // Método para realizar login
    // Salvando o token em SharedPreferences após o login
    public void loginUser(NotAuthedUserEntity notAuthedUser, final LoginCallback callback, Context context) {
        LiterarioAPI api = getLiterarioAPI();

        Call<LoginResponse> call = api.loginUser(notAuthedUser);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    if (loginResponse != null && loginResponse.getToken() != null) {
                        // Salve o token no SharedPreferences com commit síncrono
                        SharedPreferences sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("auth_token", loginResponse.getToken());
                        boolean saved = editor.commit(); // Sincroniza para garantir que o token seja salvo antes de acessar outra Activity

                        if (saved) {
                            Log.d("LoginActivity", "Token salvo: " + loginResponse.getToken());
                            // Redireciona para a BooksActivity
                            Intent intent = new Intent(context, BookActivity.class);
                            context.startActivity(intent);
                        } else {
                            Log.e("LoginActivity", "Falha ao salvar o token.");
                            callback.onFailure("Falha ao salvar o token.");
                        }
                    } else {
                        callback.onFailure("Token não encontrado na resposta");
                    }
                } else {
                    callback.onFailure("Erro no login. Código de resposta: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                callback.onFailure("Falha na requisição: " + t.getMessage());
            }
        });
    }


    // Método para enviar review
    public void postReview(UUID userId, UUID bookId, Integer rate, String reviewText, Context context) {
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
                    Log.d("RetrofitService", "Review enviada com sucesso!");
                } else {
                    // Caso ocorra algum erro
                    Log.e("RetrofitService", "Erro ao enviar review. Código de resposta: " + response.code());
                    try {
                        // Se houver corpo de erro na resposta, capture o erro
                        Log.e("RetrofitService", "Erro: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ReviewEntity> call, Throwable t) {
                // Se ocorrer alguma falha na requisição
                Log.e("RetrofitService", "Falha na requisição: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }

    // Método para obter a lista de livros
    public void getBooks(final BooksCallback callback, Context context) {
        LiterarioAPI api = getLiterarioAPI();

        Call<List<BookEntity>> call = api.getBooks();

        call.enqueue(new Callback<List<BookEntity>>() {
            @Override
            public void onResponse(Call<List<BookEntity>> call, Response<List<BookEntity>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Erro ao buscar livros. Código: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<BookEntity>> call, Throwable t) {
                callback.onFailure("Falha na requisição: " + t.getMessage());
            }
        });
    }
}
