package com.example.agoravai.services;

import com.example.agoravai.models.BookEntity;
import java.util.List;

public interface BooksCallback {
    void onSuccess(List<BookEntity> books);
    void onFailure(String errorMessage);
}
