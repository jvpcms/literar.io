package com.literario.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
@ComponentScan(basePackages = "com.literario.api")
@EnableJpaRepositories(basePackages = "com.literario.api.repo")
public class ApiApplication {
    public static void main(String[] args) {
        // Carrega as variáveis de ambiente antes de iniciar a aplicação
        Dotenv dotenv = Dotenv.configure()
                .directory(".")  // especifica o diretório do arquivo .env
                .ignoreIfMissing()
                .load();
        
        // Define as propriedades do sistema
        System.setProperty("spring.datasource.url", dotenv.get("POSTGRES_URL"));
        System.setProperty("spring.datasource.username", dotenv.get("POSTGRES_USER"));
        System.setProperty("spring.datasource.password", dotenv.get("POSTGRES_PASSWORD"));
        System.setProperty("HS256_SECRET", dotenv.get("HS256_SECRET"));
        
        SpringApplication.run(ApiApplication.class, args);
    }
}