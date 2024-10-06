package com.literario.api.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;

import java.io.IOException;
import java.util.Map;

import com.literario.api.service.AuthService;
import com.literario.api.utils.CustomResponse;
import com.literario.api.utils.Endpoints;


@Component
@Order(1)
public class TokenValidFilter extends OncePerRequestFilter {

    @Override
    public void doFilterInternal(final @NonNull HttpServletRequest request, final @NonNull HttpServletResponse response, final @NonNull FilterChain chain) 
    throws IOException, ServletException {

        String path = request.getRequestURI();
        if (Endpoints.isPublic(path)) {
            chain.doFilter(request, response);
            return;
        }

        String token = request.getHeader("authentication");
        ResponseEntity<Map<String, String>> responseEntity = AuthService.verifyToken(token);

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            CustomResponse.copyFromResponseEntity(responseEntity, response);
            response.sendRedirect("/login");
            return;
        }

        chain.doFilter(request, response);
    }

}
