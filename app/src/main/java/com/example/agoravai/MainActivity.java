package com.example.agoravai;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agoravai.services.RetrofitService;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private EditText etReview;
    private EditText etRating;
    private Button btnSubmitReview;

    private RetrofitService retrofitService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializando os componentes da interface
        etReview = findViewById(R.id.etReview);
        etRating = findViewById(R.id.etRating);
        btnSubmitReview = findViewById(R.id.btnSubmitReview);

        // Inicializando o RetrofitService
        retrofitService = new RetrofitService();

        // Configurando o botão para enviar a review
        btnSubmitReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pegando os valores do usuário
                String review = etReview.getText().toString();
                String ratingText = etRating.getText().toString();

                if (review.isEmpty() || ratingText.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Convertendo a nota para inteiro
                int rate = Integer.parseInt(ratingText);

                // Usando um ID de usuário e livro de exemplo (substitua conforme necessário)
                UUID userId = UUID.fromString("7b10a04e-0987-4fdd-a6e9-0f342915fcf1");  // ID do usuário
                UUID bookId = UUID.fromString("15f67fc2-000d-4399-b759-537efc43cc19");  // ID do livro

                // Enviando a review via Retrofit
                retrofitService.postReview(userId, bookId, rate, review);

                // Exibindo uma mensagem de sucesso
                Toast.makeText(MainActivity.this, "Review enviada com sucesso!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
