package com.example.websocketapp.controller;

import com.example.websocketapp.messaging.MessageProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KafkaTestController {

    private final MessageProducer producer;

    @GetMapping("/kafka")
    public String produce(String message) {
        producer.produce(message);

        return "success!";
    }
}
