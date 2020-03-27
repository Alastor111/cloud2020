package com.dx.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EuerkaServer7001 {

    public static void main(String[] args) {
        SpringApplication.run(EuerkaServer7001.class, args);
    }

}
