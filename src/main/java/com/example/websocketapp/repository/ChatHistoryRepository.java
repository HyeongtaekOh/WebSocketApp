package com.example.websocketapp.repository;

import com.example.websocketapp.domain.ChatHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.Instant;
import java.util.Optional;

public interface ChatHistoryRepository extends MongoRepository<ChatHistory, String> {

    Optional<ChatHistory> findByRoomIdAndUserId(Long roomId, Long userId);
}
