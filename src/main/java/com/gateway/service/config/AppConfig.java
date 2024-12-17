package com.gateway.service.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(r -> r.path("/order-api/**")
                        .filters(f -> f.rewritePath("/order-api/?(?<remaining>.*)", "/${remaining}"))
                        .uri("lb://order-service"))
                .build();
    }
}
