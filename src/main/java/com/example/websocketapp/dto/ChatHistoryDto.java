package com.example.websocketapp.dto;

import com.example.websocketapp.domain.ChatHistory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@Getter
@Setter
@Builder
@ToString
public class ChatHistoryDto {

    private Long userId;
    private Long roomId;
    private Instant firstMessageTimestamp;
    private Instant lastReadTimestamp;

    public static ChatHistoryDto of(ChatHistory chatHistory) {
        ChatHistoryDto chatHistoryDto = ChatHistoryDto.builder()
                .userId(chatHistory.getUserId())
                .roomId(chatHistory.getRoomId())
                .firstMessageTimestamp(chatHistory.getFirstMessageTimestamp())
                .lastReadTimestamp(chatHistory.getLastReadTimestamp())
                .build();
        return chatHistoryDto;
    }

    public ChatHistory toEntity() {
        ChatHistory chatHistory = ChatHistory.builder()
                .userId(userId)
                .roomId(roomId)
                .firstMessageTimestamp(firstMessageTimestamp)
                .lastReadTimestamp(lastReadTimestamp)
                .build();
        return chatHistory;
    }
}
