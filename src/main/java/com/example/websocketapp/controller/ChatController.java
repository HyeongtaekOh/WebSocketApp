package com.example.websocketapp.controller;

import com.example.websocketapp.domain.ChatMessage;
import com.example.websocketapp.dto.ChatHistoryDto;
import com.example.websocketapp.dto.ChatMessageDto;
import com.example.websocketapp.service.AmqpService;
import com.example.websocketapp.service.ChatHistoryService;
import com.example.websocketapp.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.Instant;
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
    private final ChatHistoryService chatHistoryService;
    private final AmqpService amqpService;

    @MessageMapping("/chat/{roomId}")
    public void greeting(@DestinationVariable(value = "roomId") Long roomId, @Payload ChatMessageDto messageDto) {
        log.info("roomId: {}, message: {}", roomId, messageDto);
        String routingKey = queue + "." + messageDto.getRoomId();
        log.info("routingKey: {}", routingKey);
        ChatMessage message = chatService.save(messageDto);
        amqpService.sendMessage(exchange, routingKey, message);
    }

    @ResponseBody
    @RequestMapping("/chats/{roomId}")
    public List<ChatMessage> findAllByRoomId(@PathVariable(value = "roomId") Long roomId, Long userId, Pageable pageable) {
        log.info("pageable: {}", pageable);
        ChatHistoryDto chatHistoryDto = chatHistoryService.findFirstTimeByRoomIdAndUserId(roomId, userId);
        if (chatHistoryDto == null) {
            chatHistoryDto = chatHistoryService.save(ChatHistoryDto.builder()
                    .roomId(roomId)
                    .userId(userId)
                    .firstMessageTimestamp(Instant.now())
                    .build());
        }
        log.info("chatHistoryDto: {}", chatHistoryDto);
        List<ChatMessage> messages = chatService.findAllByRoomIdAndFirstMessageTime(roomId, chatHistoryDto.getFirstMessageTimestamp(), pageable);
        log.info("messages: {}", messages);
        return messages;
    }
}
