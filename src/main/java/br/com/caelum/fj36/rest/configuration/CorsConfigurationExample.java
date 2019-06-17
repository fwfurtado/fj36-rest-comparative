package br.com.caelum.fj36.rest.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfigurationExample implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/")
            .allowedOrigins("*") //WARNING: Allow all origins
            .allowedMethods("*"); // Allow all http methods

    }
}
