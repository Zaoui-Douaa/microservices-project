package com.cosmetics.gatewayservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtDecoderConfig {

    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        return NimbusReactiveJwtDecoder
            .withJwkSetUri("http://keycloak:8080/realms/ms/protocol/openid-connect/certs")
            .build();
    }
}
