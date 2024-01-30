package com.example.websocketapp.domain;

import com.example.websocketapp.dto.ChatMessageDto;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Builder
@Document(collection = "chatMessage")
public class ChatMessage {

    @Id
    private String id;

    private Long roomId;
    private Long senderId;
    private MessageType type;
    private String content;

    @CreatedDate
    private Instant timestamp;

    public static ChatMessage of(ChatMessageDto chatMessageDto) {
        return ChatMessage.builder()
                .roomId(chatMessageDto.getRoomId())
                .senderId(chatMessageDto.getSenderId())
                .type(chatMessageDto.getType())
                .content(chatMessageDto.getContent())
                .build();
    }
}
