package com.example.websocketapp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "chatHistory")
public class ChatHistory {

    @Id
    private String id;

    private Long userId;
    private Long roomId;

    private Instant firstMessageTimestamp;
    private Instant lastReadTimestamp;
}
