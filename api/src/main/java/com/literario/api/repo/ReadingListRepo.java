package com.literario.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

import com.literario.api.model.ReadingList;

@RepositoryRestResource
public interface ReadingListRepo extends JpaRepository<ReadingList, UUID> {

    @Query("SELECT rl FROM ReadingList rl WHERE rl.user.id = :userId")
    List<ReadingList> findReadingListsByUserId(@Param("userId") UUID userId);
}
