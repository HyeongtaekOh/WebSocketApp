package com.example.websocketapp.domain;

import com.example.websocketapp.dto.ChatMessageDto;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
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
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setRoomId(chatMessageDto.getRoomId());
        chatMessage.setSenderId(chatMessageDto.getSenderId());
        chatMessage.setContent(chatMessageDto.getContent());
        return chatMessage;
    }
}
