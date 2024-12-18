package com.literario.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**") // Allow all paths
        .allowedOrigins("http://localhost:5173",
            "http://localhost:9090",
            "http://localhost:80") // Change to your frontend URL
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Specify allowed methods
        .allowedHeaders("*") // Allow all headers
        .allowCredentials(true); // Allow credentials (if needed)
  }
}
