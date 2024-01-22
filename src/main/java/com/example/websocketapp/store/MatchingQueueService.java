package com.example.websocketapp.store;

import java.util.List;

public interface MatchingQueueService {

    void addUser(String userId, Object user);

    void removeUser(String userId);

    List<Object> getAllUsers();
}
