package com.example.websocketapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class WebsocketAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebsocketAppApplication.class, args);
    }

}
