package com.example.websocketapp.dto;

import com.example.websocketapp.domain.MessageType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatMessageDto {

    private Long roomId;
    private Long senderId;
    private MessageType type;
    private String content;
}
