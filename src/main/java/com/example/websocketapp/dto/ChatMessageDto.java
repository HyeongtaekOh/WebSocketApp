package com.example.websocketapp.dto;

import lombok.Data;

@Data
public class ChatMessageDto {

    private String roomId;
    private String sender;
    private String content;
}
