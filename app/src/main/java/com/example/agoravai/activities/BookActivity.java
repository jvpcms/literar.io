package com.example.agoravai.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.agoravai.R;
import com.example.agoravai.models.BookEntity;
import com.example.agoravai.services.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class BookActivity extends AppCompatActivity {

    private TextView bookTitle;
    private TextView bookDescription;
    private RetrofitService retrofitService;
    private String bookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_book);

        bookTitle = findViewById(R.id.bookTitle);
        bookDescription = findViewById(R.id.bookSynopsis);

        // Obtendo o ID do livro passado pela BookAdapter
        Intent intent = getIntent();
        bookId = intent.getStringExtra("bookId");

        retrofitService = new RetrofitService(this);

        if (bookId != null) {
            fetchBooks();
        }
    }

    private void fetchBooks() {
        retrofitService.getLiterarioAPI().getBooks().enqueue(new Callback<List<BookEntity>>() {
            @Override
            public void onResponse(Call<List<BookEntity>> call, Response<List<BookEntity>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<BookEntity> books = response.body();
                    // Filtrando o livro com base no ID recebido
                    for (BookEntity book : books) {
                        if (book.getId().toString().equals(bookId)) {
                            bookTitle.setText(book.getTitle());
                            bookDescription.setText(book.getSynopsis());
                            return;
                        }
                    }
                    Toast.makeText(BookActivity.this, "Livro não encontrado.", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("BookActivity", "Erro ao obter os livros.");
                    Toast.makeText(BookActivity.this, "Erro ao obter os livros.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<BookEntity>> call, Throwable t) {
                Log.e("BookActivity", "Falha na requisição: " + t.getMessage());
                Toast.makeText(BookActivity.this, "Falha na requisição: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
