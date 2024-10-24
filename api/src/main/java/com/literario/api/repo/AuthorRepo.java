package com.literario.api.repo;

import com.literario.api.model.AuthorEntity;
import java.util.UUID;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AuthorRepo extends JpaRepository<AuthorEntity, UUID> {
    
    default Optional<AuthorEntity> findAuthorById(UUID authorId){
        Optional<AuthorEntity> author = findById(authorId);
        return author;
    }
}
