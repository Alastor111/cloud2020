package com.dx.cloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//配置网关路由
@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder){
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes
                .route("route_Baidu", r ->
                        r.path("/baidu").uri("https://www.baidu.com"))
                .route("route_tx",r->
                        r.path("/tx").uri("https://www.qq.com"));
        return routes.build();
    }
}
