package com.example.websocketapp.store;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class MemoryMatchingQueueService implements MatchingQueueService{

    private final ConcurrentHashMap<String, Object> users = new ConcurrentHashMap<>();

    @Override
    public void addUser(String userId, Object user) {
        users.put(userId, user.toString());
    }

    @Override
    public void removeUser(String userId) {
        users.remove(userId);
    }

    @Override
    public List<Object> getAllUsers() {
        return users.values().stream().toList();
    }
}
