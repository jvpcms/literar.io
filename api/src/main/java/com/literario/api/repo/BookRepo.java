package com.literario.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.literario.api.model.AuthorEntity;
import com.literario.api.model.BookEntity;

@RepositoryRestResource
public interface BookRepo extends JpaRepository<BookEntity, UUID> {

    @Query("SELECT b FROM BookEntity b WHERE b.year = :year")
    List<BookEntity> findBooksByYear(@Param("year") Integer year);

    default Optional<BookEntity> findBookById(UUID bookId){
        Optional<BookEntity> book = findById(bookId);
        return book;
    }

    @Query("SELECT b FROM BookEntity b WHERE b.author = :author")
    List<BookEntity> findBooksByAuthor(@Param("author") AuthorEntity author);
}

