package com.example.websocketapp.service;

import com.example.websocketapp.domain.ChatMessage;

public interface AmqpService {

    public void sendMessage(String exchange, String routingKey, ChatMessage message);
}
