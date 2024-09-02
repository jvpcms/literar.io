package com.literario.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();

        // Carregar variáveis de ambiente
        String postgresUrl = dotenv.get("POSTGRES_URL");
        String postgresUser = dotenv.get("POSTGRES_USER");
        String postgresPassword = dotenv.get("POSTGRES_PASSWORD");

        // Verificar se todas as variáveis estão definidas
        if (postgresUrl == null || postgresUser == null || postgresPassword == null) {
            throw new IllegalStateException("Algumas variáveis de ambiente não estão definidas no arquivo .env");
        }

        // Definir propriedades do sistema
        System.setProperty("POSTGRES_URL", postgresUrl);
        System.setProperty("POSTGRES_USER", postgresUser);
        System.setProperty("POSTGRES_PASSWORD", postgresPassword);

        SpringApplication.run(ApiApplication.class, args);
    }
}
