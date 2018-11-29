package com.pic.localizafilmes.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebMvc
public class CorsConfigure implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(CorsConfiguration.ALL)
                .allowedMethods("HEAD,GET,POST,PUT,DELETE,PATCH,OPTIONS".split(","))
                .allowedHeaders(("Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method," +
                        "Access-Control-Request-Headers,App-Context,App-Links,Authorization," +
                        "User-Access,Filter-Encoded").split(","));
    }

}