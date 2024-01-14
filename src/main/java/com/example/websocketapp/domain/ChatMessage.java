package com.example.websocketapp.domain;

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

}
