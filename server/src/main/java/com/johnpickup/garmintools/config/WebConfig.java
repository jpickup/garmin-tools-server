package com.johnpickup.garmintools.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        log.info("Adding CORS mappings");
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET","POST");
    }
}
