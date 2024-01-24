package com.example.websocketapp.service;

import com.example.websocketapp.domain.ChatMessage;
import com.example.websocketapp.dto.ChatMessageDto;
import com.example.websocketapp.repository.ChatHistoryRepository;
import com.example.websocketapp.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

    private final AmqpService amqpService;

    private final ChatRepository chatRepository;
    private final ChatHistoryRepository chatHistoryRepository;

    public ChatMessage save(ChatMessageDto chatMessageDto) {
        ChatMessage chatMessage = ChatMessage.of(chatMessageDto);
        return chatRepository.save(chatMessage);
    }

    @Transactional(readOnly = true)
    public List<ChatMessage> findAllByRoomIdAndFirstMessageTime(Long roomId, Instant firstMessageTime, Pageable pageable) {
        return chatRepository.findAllByRoomIdAndTimestampIsGreaterThanEqual(roomId, firstMessageTime, pageable);
    }
}
