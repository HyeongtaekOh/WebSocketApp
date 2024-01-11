package com.example.websocketapp.controller;

import com.example.websocketapp.domain.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class GreetingController {

    @MessageMapping("/chat/{roomId}")
    @SendTo("/topic/messages/{roomId}")
    public String greeting(@DestinationVariable String roomId, String message) {
        log.info("roomId: {}, message: {}", roomId, message);
        return message;
    }
}
