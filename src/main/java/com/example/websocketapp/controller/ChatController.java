package com.example.websocketapp.controller;

import com.example.websocketapp.domain.ChatMessage;
import com.example.websocketapp.dto.ChatMessageDto;
import com.example.websocketapp.service.AmqpService;
import com.example.websocketapp.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatController {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.queue.name}")
    private String queue;

    private final ChatService chatService;
    private final AmqpService amqpService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat/{roomId}")
    public void greeting(@DestinationVariable(value = "roomId") String roomId, @Payload ChatMessageDto messageDto) {
        log.info("roomId: {}, message: {}", roomId, messageDto);
        String routingKey = queue + "." + messageDto.getRoomId();
        log.info("routingKey: {}", routingKey);
        ChatMessage message = chatService.save(messageDto);
        amqpService.sendMessage(exchange, routingKey, message);
    }

    @ResponseBody
    @RequestMapping("/chats/{roomId}")
    public List<ChatMessage> findAllByRoomId(@PathVariable(value = "roomId") String roomId) {
        List<ChatMessage> messages = chatService.findAllByRoomId(roomId);
        log.info("messages: {}", messages);
        return messages;
    }
}
