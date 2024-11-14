package com.example.agoravai.activities;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.agoravai.R;
import com.example.agoravai.models.BookEntity;
import com.example.agoravai.services.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class BookActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView headerText;
    private RetrofitService retrofitService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        recyclerView = findViewById(R.id.recyclerViewBooks);
        headerText = findViewById(R.id.headerText);

        // Configurando o texto no topo da página
        headerText.setText("Clique no livro para fazer a review");

        // Inicializando Retrofit
        retrofitService = new RetrofitService(this);

        // Buscando livros
        fetchBooks();
    }

    private void fetchBooks() {
        retrofitService.getLiterarioAPI().getBooks().enqueue(new Callback<List<BookEntity>>() {
            @Override
            public void onResponse(Call<List<BookEntity>> call, Response<List<BookEntity>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<BookEntity> books = response.body();
                    setupRecyclerView(books);
                } else {
                    Toast.makeText(BookActivity.this, "Erro ao carregar livros.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<BookEntity>> call, Throwable t) {
                Toast.makeText(BookActivity.this, "Falha na requisição: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupRecyclerView(List<BookEntity> books) {
        BookAdapter bookAdapter = new BookAdapter(this, books);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(bookAdapter);
    }
}
