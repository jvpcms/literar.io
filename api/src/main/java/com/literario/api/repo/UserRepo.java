package com.literario.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

import com.literario.api.model.UserEntity;

@RepositoryRestResource
public interface UserRepo extends JpaRepository<UserEntity, UUID> {

    @Query("SELECT u FROM UserEntity u WHERE u.username = :username")
    List<UserEntity> findByUsername(@Param("username") String username);

    default Optional<UserEntity> findUserById(UUID userId){
        Optional<UserEntity> user = findById(userId);
        return user;
    };

}
