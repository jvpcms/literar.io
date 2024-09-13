package com.literario.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

import com.literario.api.model.BookEntity;

@RepositoryRestResource
public interface BookRepo extends JpaRepository<BookEntity, UUID> {


    @Query("SELECT b FROM BookEntity b WHERE b.year = :year")
    List<BookEntity> findBooksByYear(@Param("year") Integer year);

    @Query("SELECT b FROM Book b WHERE b.author_id = :author_id")
    List<BookEntity> findBooksByAuthor(@Param("author_id") UUID author_id);

    @Query("SELECT b FROM Book b WHERE b.title = :title")
    List<BookEntity> findBooksByTitle(@Param("title") String title);
}

