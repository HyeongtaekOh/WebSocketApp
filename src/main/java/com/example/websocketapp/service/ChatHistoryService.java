package com.example.websocketapp.service;

import com.example.websocketapp.domain.ChatHistory;
import com.example.websocketapp.dto.ChatHistoryDto;
import com.example.websocketapp.repository.ChatHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatHistoryService {

    private final ChatHistoryRepository chatHistoryRepository;

    public ChatHistoryDto save(ChatHistoryDto chatHistoryDto) {
        ChatHistory save = chatHistoryRepository.save(chatHistoryDto.toEntity());
        return ChatHistoryDto.of(save);
    }

    public ChatHistoryDto findFirstTimeByRoomIdAndUserId(Long roomId, Long userId) {
        Optional<ChatHistory> byRoomIdAndUserId = chatHistoryRepository.findByRoomIdAndUserId(roomId, userId);
        return byRoomIdAndUserId.map(ChatHistoryDto::of).orElse(null);
    }
}
