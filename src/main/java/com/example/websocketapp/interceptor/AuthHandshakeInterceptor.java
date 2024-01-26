package com.example.websocketapp.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Slf4j
public class AuthHandshakeInterceptor implements HandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        // 클라이언트 인증 정보 추출 (예: 토큰)
//        String token = request.getHeaders().getFirst("Authorization");
        // 또는 쿼리 파라미터에서 추출
        if (request.getURI().getQuery() == null)
            return true;

         String userId = request.getURI().getQuery().split("userId=")[1];
         log.info("userId: {}", userId);
        // 또는 쿠키에서 추출
        // String token = request.getHeaders().getFirst("Cookie").split("=")[1];

        // 인증 처리 로직 (토큰 검증 등)
        // 생략

        // 인증 정보를 attributes에 저장
        attributes.put("userId", userId);
        log.info("attributes: {}", attributes);
        return true; // false를 반환하면 연결 거부
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        // 후처리 로직
    }
}
