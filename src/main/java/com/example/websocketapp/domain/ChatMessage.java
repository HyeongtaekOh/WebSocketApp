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

    private String roomId;
    private String sender;
    private String content;

    @CreatedDate
    private Instant createdAt;

    public static ChatMessage of(ChatMessageDto chatMessageDto) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setRoomId(chatMessageDto.getRoomId());
        chatMessage.setSender(chatMessageDto.getSender());
        chatMessage.setContent(chatMessageDto.getContent());
        return chatMessage;
    }
}
