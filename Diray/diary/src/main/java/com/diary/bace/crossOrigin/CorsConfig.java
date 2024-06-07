package com.diary.bace.crossOrigin;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域配置
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

//    private static final String[] ORIGINS = {
//            "http://127.0.0.1:8080",
//            "http://127.0.0.1:8081",
//            "http://localhost:8080",
//            "http://localhost:8081"
//    };

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(false)
                .allowedMethods("*")
                .maxAge(3600);
    }
}
