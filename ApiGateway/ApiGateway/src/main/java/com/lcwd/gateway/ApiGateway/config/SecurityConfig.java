package com.lcwd.gateway.ApiGateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange(exchange ->
                        exchange
                                .anyExchange().authenticated()
                )
                .oauth2Client(client -> {}) // Configure OAuth2 Client (no explicit return)
                .oauth2ResourceServer(server -> server.jwt(jwt -> {
                    // You can further customize the JWT configuration here if needed
                }));

        return http.build();
    }
}