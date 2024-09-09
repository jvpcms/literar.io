package com.literario.api;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootTest
@AutoConfigureMockMvc
class UserTests {

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public static void setup() {
        
        Dotenv dotenv = Dotenv.load();

		System.setProperty("POSTGRES_URL", dotenv.get("POSTGRES_URL"));
        System.setProperty("POSTGRES_USER", dotenv.get("POSTGRES_USER"));
        System.setProperty("POSTGRES_PASSWORD", dotenv.get("POSTGRES_PASSWORD"));
		System.setProperty("HS256_SECRET", dotenv.get("HS256_SECRET"));

    }

    @Test
    void testRegisterUser() {

        String randomUsername = "testuser " + Math.random();

        try {
            mockMvc.perform(post("/users/register")
                .contentType("application/json")
                .content("{\"username\": \"" + randomUsername + "\", \"password\": \"testpassword\"}"))
                .andExpect(status().isCreated());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void registerExistingUser() {
        try {
            mockMvc.perform(post("/users/register")
                .contentType("application/json")
                .content("{\"username\": \"testuser\", \"password\": \"testpassword\"}"))
                .andExpect(status().isConflict());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   
}
