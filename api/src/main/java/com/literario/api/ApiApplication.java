package com.literario.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class ApiApplication {

  public static void main(String[] args) {

    Dotenv dotenv = Dotenv.load();

    System.setProperty("POSTGRES_URL", dotenv.get("POSTGRES_URL"));
    System.setProperty("POSTGRES_USER", dotenv.get("POSTGRES_USER"));
    System.setProperty("POSTGRES_PASSWORD", dotenv.get("POSTGRES_PASSWORD"));
    System.setProperty("HS256_SECRET", dotenv.get("HS256_SECRET"));

    SpringApplication.run(ApiApplication.class, args);
  }

}
