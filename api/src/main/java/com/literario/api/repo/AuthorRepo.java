package com.literario.api.repo;

import com.literario.api.model.AuthorEntity;
import java.util.UUID;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepo extends JpaRepository<AuthorEntity, UUID> {
    
    default Optional<AuthorEntity> findAuthorById(UUID authorId){
        return findById(authorId);
    }
}
