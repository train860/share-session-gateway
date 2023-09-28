package com.ttt.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.annotation.web.server.EnableRedisWebSession;

@SpringBootApplication
public class JtopGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(JtopGatewayApplication.class, args);
    }
}
