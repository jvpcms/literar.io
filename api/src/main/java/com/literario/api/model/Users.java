package com.literario.api.model;

import lombok.Data;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.UUID;

@Data
@Entity
@Table(name="users")
public class Users {
    
    @Id
    private UUID id;
    private String created_at;
    private String username;
    private String salt;
    private String hash;	

}
