package com.poolamigos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //le dice a Spring "esta clase declara beans, procesala al arrancar"
public class SecurityConfig {

    /*
        Un bean de tipo SecurityFilterChain, la condición @ConditionalOnMissingBean de SecurityAutoConfiguration deja de cumplirse,
        y toda esa configuración por defecto (usuario random, contraseña random, CSRF activado) desaparece.
         Lo que quede en este metodo es la única verdad ahora.
    */

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        return http.build();
    }
}