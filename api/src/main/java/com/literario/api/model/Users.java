package com.literario.api.model;

import lombok.Data;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Data
@Entity
@Table(name="users")
public class Users {
    
    @Id
    private String id;
    private String created_at;
    private String username;
    private String salt;
    private String hash;	

}
