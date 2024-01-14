package com.example.websocketapp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageDto {

    private String roomId;
    private String sender;
    private String content;
}
