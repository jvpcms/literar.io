package com.literario.api.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;

import java.io.IOException;
import java.util.UUID;

import com.literario.api.service.AuthService;
import com.literario.api.utils.CustomResponse;
import com.literario.api.utils.Endpoints;


@Component
@Order(2)
public class UserSpecificRequestFilter extends OncePerRequestFilter {

    @Override
    public void doFilterInternal(final @NonNull HttpServletRequest request, final @NonNull HttpServletResponse response, final @NonNull FilterChain chain) 
    throws IOException, ServletException {

        String path = request.getRequestURI();
        if (!Endpoints.isUserSpecific(path)) {
            chain.doFilter(request, response);
            return;
        }
        
        String token = request.getHeader("authentication");
        UUID userId;

        try {
            userId = UUID.fromString(request.getHeader("user_id"));
            if (AuthService.verifyId(token, userId) == null || !AuthService.verifyId(token, userId)) {
                throw new IllegalArgumentException("Invalid user ID");
            }
        } catch (Exception e) {
            CustomResponse.copyFromResponseEntity(CustomResponse.invalidUserId(), response);
            return;
        }
        
        chain.doFilter(request, response);
    }

}
