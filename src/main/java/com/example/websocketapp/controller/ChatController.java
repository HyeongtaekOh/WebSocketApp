package com.example.websocketapp.controller;

import com.example.websocketapp.domain.ChatMessage;
import com.example.websocketapp.dto.ChatMessageDto;
import com.example.websocketapp.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @MessageMapping("/chat/{roomId}")
    @SendTo("/topic/messages/{roomId}")
    public ChatMessage greeting(@DestinationVariable(value = "roomId") String roomId, @Payload ChatMessageDto messageDto) {
        log.info("roomId: {}, message: {}", roomId, messageDto);
        return chatService.save(messageDto);
    }

    @ResponseBody
    @RequestMapping("/chats/{roomId}")
    public List<ChatMessage> findAllByRoomId(@PathVariable(value = "roomId") String roomId) {
        List<ChatMessage> messages = chatService.findAllByRoomId(roomId);
        log.info("messages: {}", messages);
        return messages;
    }
}
