package com.example.websocketapp.repository;

import com.example.websocketapp.domain.ChatMessage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.Instant;
import java.util.List;

public interface ChatRepository extends MongoRepository<ChatMessage, String> {

    List<ChatMessage> findAllByRoomIdAndTimestampIsGreaterThanEqual(Long roomId, Instant timestamp, Pageable pageable);
}
