package com.literario.api.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.literario.api.model.Author;


@RepositoryRestResource
public interface AuthorRepo extends JpaRepository<Author, UUID> {
}