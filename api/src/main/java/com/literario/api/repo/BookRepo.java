package com.literario.api.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.literario.api.model.Book;

@RepositoryRestResource
public interface BookRepo extends JpaRepository<Book, UUID> {

    @Query("SELECT b FROM Book b WHERE b.year = :year")
    List<Book> findBooksByYear(@Param("year") Integer year);
}
