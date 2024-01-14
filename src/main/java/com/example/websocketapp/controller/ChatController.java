package com.example.websocketapp.controller;

import com.example.websocketapp.domain.ChatMessage;
import com.example.websocketapp.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ChatMessage greeting(@DestinationVariable String roomId, @RequestBody ChatMessage message) {
        log.info("roomId: {}, message: {}", roomId, message);
        chatService.save(message);
        return message;
    }

    @ResponseBody
    @RequestMapping("/chats/{roomId}")
    public List<ChatMessage> findAllByRoomId(@PathVariable String roomId) {
        List<ChatMessage> messages = chatService.findAllByRoomId(roomId);
        log.info("messages: {}", messages);
        return messages;
    }
}