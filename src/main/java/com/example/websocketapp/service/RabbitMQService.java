package com.example.websocketapp.service;

import com.example.websocketapp.domain.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitMQService implements AmqpService {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void sendMessage(String exchange, String routingKey, ChatMessage message) {
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}
