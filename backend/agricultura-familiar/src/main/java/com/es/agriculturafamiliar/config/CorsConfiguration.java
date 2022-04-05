package com.es.agriculturafamiliar.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class CorsConfiguration implements WebMvcConfigurer {
    private static final String[] ALLOWED_METHODS = {"HEAD", "GET", "PUT", "POST", "DELETE", "PATCH"};

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedMethods(ALLOWED_METHODS)
            .allowedHeaders("*")
            .allowedOriginPatterns("*")
            .allowCredentials(true);
    }
    
}
