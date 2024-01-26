package com.example.websocketapp.config;

import com.example.websocketapp.interceptor.AuthHandshakeInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Slf4j
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketMessageBrokerConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * 클라이언트에서 WebSocket을 연결할 때 사용할 URL을 지정합니다.
     *
     * @param registry WebSocketEndpoint를 등록할 때 사용합니다.
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 채팅 서비스에 사용할 웹 소켓 엔드포인트를 "/chat"으로 설정합니다.
        registry
                .addEndpoint("/chat")
                .setAllowedOriginPatterns("*")
                .withSockJS();
//                .setInterceptors(new AuthHandshakeInterceptor());
        // 매칭 서비스에 사용할 웹 소켓 엔드포인트를 "/match"로 설정합니다.
        registry
                .addEndpoint("/matching")
                .setAllowedOriginPatterns("*")
                .withSockJS()
                .setInterceptors(new AuthHandshakeInterceptor());;
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry
                .setPathMatcher(new AntPathMatcher("."))
                .setApplicationDestinationPrefixes("/pub")
                .enableStompBrokerRelay("/topic")
                .setRelayHost("localhost")
                .setRelayPort(61613)
                .setClientLogin("ssafy")
                .setClientPasscode("ssafy")
                .setSystemLogin("ssafy")
                .setSystemPasscode("ssafy");
    }
}
