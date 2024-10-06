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

import com.literario.api.utils.Endpoints;
import com.literario.api.utils.CustomResponse;


@Component
@Order(0)
public class TokenPresentFilter extends OncePerRequestFilter {

    @Override
    public void doFilterInternal(final @NonNull HttpServletRequest request, final @NonNull HttpServletResponse response, final @NonNull FilterChain chain) 
    throws IOException, ServletException {

        // check if is a endpoint that does not require authentication
        String path = request.getRequestURI();

        if (Endpoints.isPublic(path)) {
            chain.doFilter(request, response);
            return;
        }

        // Check if 'authentication' header is present and not empty
        String authHeader = request.getHeader("authentication");
        if (authHeader == null || authHeader.isEmpty()) {
            CustomResponse.copyFromResponseEntity(CustomResponse.tokenNotPresent(), response);
            response.sendRedirect("/login");            
            return;
        }

        chain.doFilter(request, response);
    }

}
