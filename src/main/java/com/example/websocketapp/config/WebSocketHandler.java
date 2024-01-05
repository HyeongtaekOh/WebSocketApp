package com.example.websocketapp.config;

import com.example.websocketapp.domain.Message;
import com.example.websocketapp.utils.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class WebSocketHandler extends TextWebSocketHandler {

    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        var sessionId = session.getId();
        sessions.put(sessionId, session);

        log.info("접속 세션 : {}", session.toString());

        Message message = Message.builder()
                .sender(sessionId)
                .receiver("all")
                .build();

        message.newConnect();

        sessions.values().forEach(s -> {
            try {
                if (!s.getId().equals(sessionId)) {
                    s.sendMessage(new TextMessage(Utils.getJsonString(message)));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {

        Message message = Utils.getObject(textMessage.getPayload());
        message.setSender(session.getId());

        String receiverString = message.getReceiver();

//        if (receiverString.equals("all")) {
//            sessions.values().stream()
//                    .filter(s -> !s.getId().equals(receiverString) && s.isOpen())
//                    .forEach(s -> s.sendMessage(new TextMessage(Utils.getJsonString(message))));
//        }

        WebSocketSession receiver = sessions.get(receiverString);
        log.info("message : {}", message);
        if (receiver != null && receiver.isOpen()) {
            receiver.sendMessage(new TextMessage(Utils.getJsonString(message)));
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

        String sessionId = session.getId();

        sessions.remove(sessionId);

        Message message = new Message();
        message.closeConnect();
        message.setSender(sessionId);

        sessions.values().forEach(s -> {
            try {
                s.sendMessage(new TextMessage(Utils.getJsonString(message)));
            } catch (Exception e) {

            }
        });
    }
}
