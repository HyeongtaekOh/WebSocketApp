package com.example.websocketapp.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

@Slf4j
@Component
public class WebSocketEventListener {

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        // 여기서 클라이언트의 세션 정보나 다른 정보를 가져올 수 있습니다.
        String sessionId = headerAccessor.getSessionId();
        log.info("Received a new web socket connection. Session ID: {}", sessionId);
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionConnectedEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        // 여기서 클라이언트의 세션 정보나 다른 정보를 가져올 수 있습니다.
        String sessionId = headerAccessor.getSessionId();
        log.info("Disconnected. Session ID: {}", sessionId);
    }
}
