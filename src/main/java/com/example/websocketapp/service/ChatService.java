package com.example.websocketapp.service;

import com.example.websocketapp.domain.ChatMessage;
import com.example.websocketapp.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;

    public ChatMessage save(ChatMessage chatMessage) {
        return chatRepository.save(chatMessage);
    }

    public List<ChatMessage> findAllByRoomId(String roomId) {
        return chatRepository.findAllByRoomId(roomId);
    }
}
