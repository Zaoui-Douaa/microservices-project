package com.cosmetics.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient 
@SpringBootApplication
public class GatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}

	/*@Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("products-service", r -> r.path("/api/products/**")
                .filters(f -> f.stripPrefix(1))
                .uri("lb://products-service"))
            .route("brands-service", r -> r.path("/api/brands/**")
                .filters(f -> f.stripPrefix(1))
                .uri("lb://brands-service"))
            .build();
    }*/

}
