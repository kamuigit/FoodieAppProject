package com.niit.ApiGateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class config {
    @Bean
    public RouteLocator myRoute(RouteLocatorBuilder builder)
    {
        return builder.routes()
                .route(p->p
                        .path("/api/user/v1/**")
                        .uri("http://localhost:8085/"))
                .route(p->p
                        .path("/api/favouriteService/v1/**")
                        .uri("http://localhost:8083/"))
                .route(p->p
                        .path("/foodieservice/**")
                        .uri("http://localhost:8081/"))
                .route(p->p
                        .path("/food/resturant/**")
                        .uri("http://localhost:8081/"))
                .route(p->p
                        .path("/api/v1/**")
                        .uri("http://localhost:8081/"))
                .route(p->p
                        .path("/api/order/v1/**")
                        .uri("http://localhost:8082/"))
                .build();
    }
}
