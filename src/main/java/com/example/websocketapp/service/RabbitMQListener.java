package com.example.websocketapp.service;

import com.example.websocketapp.domain.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitMQListener {

    private final SimpMessagingTemplate messagingTemplate;

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void receiveMessage(ChatMessage message) {
        log.info("message: {}", message);
        log.info("message.getRoomId(): {}", message.getRoomId());
        log.info("messagingTemplate.messageChannel: {}", messagingTemplate.getMessageChannel());
        log.info("messagingTemplate.userPrefix: {}", messagingTemplate.getUserDestinationPrefix());
        messagingTemplate.convertAndSend("/topic/messages/" + message.getRoomId(), message);
    }
}
