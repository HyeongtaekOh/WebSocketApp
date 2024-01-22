package com.example.websocketapp.config;

import com.example.websocketapp.store.MatchingQueueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketEventListener {

    private final MatchingQueueService matchingQueueService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String userId = (String) headerAccessor.getSessionAttributes().get("userId");
        if (userId == null) {
            return;
        }
        log.info("Received a new web socket connection. User ID: {}", userId);
        matchingQueueService.addUser(userId, userId);

    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        // 여기서 클라이언트의 세션 정보나 다른 정보를 가져올 수 있습니다.
        String userId = (String) headerAccessor.getSessionAttributes().get("userId");
        log.info("사용자 {}의 연결이 종료되었습니다", userId);
        matchingQueueService.removeUser(userId);
        List<Object> users = matchingQueueService.getAllUsers();
        Map<String, Object> message = new HashMap<>();
        message.put("type", "connectedUser");
        message.put("content", users);
        simpMessagingTemplate.convertAndSend("/topic/matching", message);
    }

    @EventListener
    public void handleSubscribeEvent(SessionSubscribeEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        List<Object> users = matchingQueueService.getAllUsers();
        Map<String, Object> message = new HashMap<>();
        message.put("type", "connectedUser");
        message.put("content", users);
        simpMessagingTemplate.convertAndSend("/topic/matching", message);
    }
}
