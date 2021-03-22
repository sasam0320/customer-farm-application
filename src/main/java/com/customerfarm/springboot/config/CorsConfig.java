package com.customerfarm.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("http://localhost:4200").allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("Content-Type", "Authorization", "X-Requested-With", "X-Auth-Token", "Accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Allow-Headers","Access-Control-Expose-Headers","Access-Control-Allow-Credentials","Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Authorization")
                .allowCredentials(true).maxAge(3600);
    }

}
