package com.example.websocketapp.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageConsumer {

    @KafkaListener(topics = {"temp"}, groupId = "group1")
    public void consume(Object message) {
      log.info("Consumed message : " + message);
    }
}
