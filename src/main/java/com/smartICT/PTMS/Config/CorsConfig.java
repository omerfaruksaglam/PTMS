package com.smartICT.PTMS.Config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
@Configuration
public class CorsConfig implements CorsConfigurationSource{

    @Override
    @Bean
    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.addAllowedOrigin("http://localhost:8080");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return  configuration;
    }
}

    /*
    public void addCorsMappings(CorsRegistry registry) {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://example.com"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
        corsConfiguration.setExposedHeaders(Arrays.asList("X-Custom-Header"));
        corsConfiguration.setMaxAge(3600L);

        registry.addMapping("/api/**")
                .allowedOrigins("http://example.com")
                .allowedMethods("GET", "POST", "PUT")
                .allowedHeaders("Content-Type", "Authorization")
                .exposedHeaders("X-Custom-Header")
                .maxAge(3600L);
    }
 */

