package com.fiap.autenticationOauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/*
configuração para utilizar com autenticação do google
* */

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(authorizeRequests -> {
                    authorizeRequests
                            .requestMatchers("/public").permitAll()
                            .requestMatchers("/logout").permitAll()
                            .anyRequest().authenticated();
                })
                // Cenário A: Login inicial pelo navegador usando a conta Google
                .oauth2Login(Customizer.withDefaults())

                // Cenário B: Requisições via Postman/API enviando o Bearer Token do Google
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .build();
    }

}
