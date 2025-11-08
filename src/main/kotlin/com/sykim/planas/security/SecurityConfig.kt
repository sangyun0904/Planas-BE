package com.sykim.planas.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class SecurityConfig : WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins(
                "http://localhost:3000",
                "http://127.0.0.1:3000"
            )
            .allowedMethods("GET","POST","PUT","PATCH","DELETE","OPTIONS")
            .allowedHeaders("*")
            .exposedHeaders("Authorization","Link","X-Total-Count")
            .allowCredentials(true)
            .maxAge(3600)
    }

}