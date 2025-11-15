package com.twoxplusone.rshardware.rs_hardware_glass_and_electrical.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
public class SpringSecurity {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .authorizeHttpRequests(request->request.anyRequest().permitAll())
                .csrf(csrf->csrf.disable())
                .cors(cors->cors.configurationSource(request -> {
                    var corsConfiguration = new CorsConfiguration();
                    corsConfiguration.setAllowCredentials(true);
                    corsConfiguration.setAllowedOriginPatterns(List.of("*")); // allow any origin
                    corsConfiguration.setAllowedMethods(List.of("GET","POST","PUT","DELETE","PATCH"));
                    corsConfiguration.setAllowedHeaders(List.of("*"));
                    return corsConfiguration;
                }))
                .build();
    }
}
