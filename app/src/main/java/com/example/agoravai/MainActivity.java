package com.example.agoravai;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agoravai.services.RetrofitService;

import java.util.UUID;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class MainActivity extends AppCompatActivity {

    private EditText etReview;
    private RatingBar etRating;
    private Button btnSubmitReview;

    private RetrofitService retrofitService;
    private UUID bookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializando os componentes da interface
        etReview = findViewById(R.id.etReview);
        etRating = findViewById(R.id.etRating);
        btnSubmitReview = findViewById(R.id.btnSubmitReview);

        // Inicializando o RetrofitService
        retrofitService = new RetrofitService(this);

        // Obtendo o ID do livro passado pela Intent
        Intent intent = getIntent();
        String bookIdString = intent.getStringExtra("bookId");
        if (bookIdString != null) {
            bookId = UUID.fromString(bookIdString);  // Agora você tem o ID do livro
        }

        // Configurando o botão para enviar a review
        btnSubmitReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pegando os valores do usuário
                String review = etReview.getText().toString();
                float ratingValue = etRating.getRating();  // Obtenha o valor da RatingBar

                if (!isValidReview(review)) {
                    Toast.makeText(MainActivity.this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Convertendo a nota para Integer
                Integer rate = (int) ratingValue;

                // Obtendo o token de autenticação do SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
                String token = sharedPreferences.getString("auth_token", null);

                if (token != null) {
                    UUID userId = getUserIdFromToken(token);  // Extrai o userId do token

                    if (userId != null) {
                        // Enviando a review via Retrofit
                        retrofitService.postReview(userId, bookId, rate, review, MainActivity.this);

                        // Exibindo uma mensagem de sucesso
                        Toast.makeText(MainActivity.this, "Review enviada com sucesso!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Erro ao extrair usuário do token. Faça login novamente.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Token não encontrado. Faça o login novamente.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private UUID getUserIdFromToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey("1234")  // A chave secreta usada para gerar o token (deve ser a mesma usada no backend)
                    .parseClaimsJws(token)
                    .getBody();

            return UUID.fromString(claims.get("user_id", String.class));  // Obtém o user_id do payload do token
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean isValidReview(String review) {
        return !review.isEmpty();
    }
}
