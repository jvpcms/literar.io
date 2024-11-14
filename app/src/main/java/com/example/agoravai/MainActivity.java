package com.example.agoravai;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agoravai.services.RetrofitService;

import java.security.Key;
import java.security.SignatureException;
import java.util.UUID;

import java.util.Base64;
import java.security.Key;

import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

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
            bookId = UUID.fromString(bookIdString); // Agora você tem o ID do livro
        }

        // Configurando o botão para enviar a review
        btnSubmitReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pegando os valores do usuário
                String review = etReview.getText().toString();
                float ratingValue = etRating.getRating(); // Obtenha o valor da RatingBar

                if (!isValidReview(review)) {
                    Toast.makeText(MainActivity.this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Convertendo a nota para Integer
                Integer rate = (int) ratingValue;

                // Obtendo o token de autenticação do SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                String token = sharedPreferences.getString("auth_token", null);

                if (token != null) {
                    UUID userId = getUserIdFromToken(token); // Extrai o userId do token

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



    private boolean isValidReview(String review) {
        return !review.isEmpty();
    }

    private UUID getUserIdFromToken(String token) {
        try {
            // Sua chave secreta longa em formato String (garanta que seja a mesma no backend)
            String secretKey = "c9b7ea623c10d528b07de40fc8e076f464d050811b4b37c8fd0a21fc9f3615d2112f4d78f7fa36e94d54ddfa6b389b9f67a40e052ea40109e3938ce47a893a58f09658d51df45e04ed32cac08734814e85d2aeb0e13603f9c636b901f96f739155ededb180e801df2247b75ddbf8c6e23c00edf749d760f094cf1a13e496ab8409cca04adb0d76dcb8d227a6b42fafaa6976172a5ddf65a0eb1bc190df78af8a8850f4f3a2b36af19507d2dd63a733d0e89f0750cabb9f56e635662f62edbf94bca1e2b1b4ac9c0ed06f139c29b41bce48b4178273b9b1f42502edc573536a7e8a8b3eb41ced908b6ae765dbd7e33c000a58a99689b378c14c86519b67b1a189";

            // Criando a chave de assinatura (signingKey) a partir da chave secreta usando o algoritmo HS256
            byte[] secretKeyBytes = null;  // Decodificando a chave, se necessário
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                secretKeyBytes = Base64.getDecoder().decode(secretKey);
            }
            Key signingKey = new SecretKeySpec(secretKeyBytes, "HmacSHA256");  // Usando o algoritmo HMAC-SHA256

            // Verificando o token antes de extrair as claims
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(signingKey)  // Definindo a chave de assinatura
                    .build()
                    .parseClaimsJws(token)     // Parseando o token JWT
                    .getBody();                // Obtendo o corpo do JWT (as claims)

            // Obtendo o user_id como string do payload
            String userIdString = claims.get("user_id", String.class);

            // Verificando se o user_id é válido e tentando converter para UUID
            if (userIdString != null && !userIdString.isEmpty()) {
                return UUID.fromString(userIdString); // Convertendo a string para UUID
            } else {
                throw new IllegalArgumentException("user_id não encontrado ou inválido no token");
            }
        } catch (Exception e) {
            Log.e("JWT", "Erro ao extrair user_id do token: " + e.getMessage());
        }
        return null;
    }
}

