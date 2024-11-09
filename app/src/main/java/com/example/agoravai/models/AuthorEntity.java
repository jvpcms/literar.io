package com.example.agoravai.models;

import com.google.gson.annotations.SerializedName;
import java.util.UUID;

public class AuthorEntity {

    @SerializedName("id")
    private UUID id;

    @SerializedName("name")
    private String name;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AuthorEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
