package com.literario.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.literario.api.service.AuthService;

import io.jsonwebtoken.Claims;

@SpringBootTest
@AutoConfigureMockMvc
class AuthTests {

    @Autowired
    private MockMvc mockMvc;

    private static final String EXPIRED_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJqdnBzb3V6YSIsInVzZXJfaWQiOiJiNmIzNjNkZi1kZTc2LTQ3MWItYTY2Mi1iNTNkZTZiMGNhMjUiLCJpYXQiOjE3MjU2Njk2NzAsImV4cCI6MTcyNTY3MzI3MH0.guMmWTBO_A1A-mv9n0uHyBv1C0xZqilwyGfQ5i-TaCw";
    private static final String INVALID_SIGN_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJqdnBzb3V6YSIsInVzZXJfaWQiOiJiNmIzNjNkZi1kZTc2LTQ3MWItYTY2Mi1iNTNkZTZiMGNhMjUiLCJpYXQiOjE3MjU2Njk2NzAsImV4cCI6MTcyNTY3MzI3MH0.guMmWTBO_A1A-mv9n0uHyBv1C0xZqilwyGfQ5i-TaC";
    private static final String NOT_EXPIRED_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJqdnBzb3V6YSIsInVzZXJfaWQiOiJiNmIzNjNkZi1kZTc2LTQ3MWItYTY2Mi1iNTNkZTZiMGNhMjUiLCJpYXQiOjE3MjU3MjAzMzAsImV4cCI6MTcyNzE5MTU1OX0.2VvXVFPn7gr23AQjwAp6B173NVSTLLkS6-JEMfs4agg";
    private static final Claims CLAIMS = AuthService.extractAllClaims(NOT_EXPIRED_TOKEN);

    @Test
    void checkId() {
        UUID id = UUID.fromString("b6b363df-de76-471b-a662-b53de6b0ca25");
        assertEquals(true, AuthService.verifyId(CLAIMS, id));
        assertEquals(false, AuthService.verifyId(CLAIMS, UUID.randomUUID()));
    }

    @Test
    void testValidateToken() {
        try {
            mockMvc.perform(post("/auth/validate")
                .header("token", NOT_EXPIRED_TOKEN)
                .header("id", "b6b363df-de76-471b-a662-b53de6b0ca25"))
                .andExpect(status().isOk());
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    @Test
    void testValidateExpiredToken() {
        try {
            mockMvc.perform(post("/auth/validate")
                .header("token", EXPIRED_TOKEN)
                .header("id", "b6b363df-de76-471b-a662-b53de6b0ca25"))
                .andExpect(status().isUnauthorized());
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    @Test
    void testValidateInvalidSignature() {
        try {
            mockMvc.perform(post("/auth/validate")
                .header("token", INVALID_SIGN_TOKEN)
                .header("id", "b6b363df-de76-471b-a662-b53de6b0ca25"))
                .andExpect(status().isUnauthorized());
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    @Test
    void testVaidateWrongId() {
        try {
            mockMvc.perform(post("/auth/validate")
                .header("token", NOT_EXPIRED_TOKEN)
                .header("id", UUID.randomUUID().toString()))
                .andExpect(status().isForbidden());
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

}
