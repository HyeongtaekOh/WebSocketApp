package com.example.websocketapp.dto;

import com.example.websocketapp.domain.MessageType;
import lombok.Data;

@Data
public class ChatMessageDto {

    private Long roomId;
    private Long senderId;
    private MessageType type;
    private String content;
}
