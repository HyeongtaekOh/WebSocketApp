package com.example.websocketapp.schedule;

import com.example.websocketapp.store.MatchingQueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ScheduledMatching {

    private final MatchingQueueService matchingQueueService;
    private final SimpMessagingTemplate messagingTemplate;

    @Scheduled(fixedDelay = 10000)
    public void matching() {
        List<Object> users = matchingQueueService.getAllUsers();
        List<Object> matchedUsers = users.size() > 2 ? users.subList(0, 2) : users;
        Map<String, Object> message = new HashMap<>();
        message.put("type", "matching");
        message.put("content", matchedUsers);
        messagingTemplate.convertAndSend("/topic/matching", message);
    }
}
