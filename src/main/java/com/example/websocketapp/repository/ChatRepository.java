package com.example.websocketapp.repository;

import com.example.websocketapp.domain.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatRepository extends MongoRepository<ChatMessage, String> {

    List<ChatMessage> findAllByRoomId(Long roomId);
}
