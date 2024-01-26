# WebSocketApp

WebSocket 기반 STOMP 통신을 통해 구현한 실시간 그룹 채팅 서버이다.

### 실행 환경 설정

Spring 서버를 실행하기 전 docker compose 파일(`docker-compose.yml`)로 RabbitMQ, MongoDB 컨테이너를 실행해줘야한다.

`docker-compose.yml` 파일이 있는 위치에서 아래 명령어를 입력해주면 각 컨테이너를 background로 실행한다.
```
docker compose up -d
```

### 브랜치 설명

* 그룹채팅 + 채팅 데이터 MongoDB에 백업
  * `no-message-broker`: 메시지 브로커 없이 Spring WebSocket만을 이용해서 구현한 버전
  * 'message-broker' : 메시지 브로커를 추가한 버전으로, Spring AMQP 모듈에서 지원하는. RabbitMQ configuration과 Listener 등을 이용해서 `no-message-broker` 버전과 거의 동일하게 동작하게 구현한 버전
* 그룹채팅 + 채팅 데이터 MongoDB에 백업 + 외부 메시지 브로커 + 사용자가 채팅방에 접속한 시점 이후에 주고받은 메시지만 조회
  * `main`, `stomp-broker-relay` : Spring WebSocket STOMP 통신에서 지원하는 형태로. RabbitMQ + STOMP plugin을 연동한 버전

### 채팅 클라이언트 테스트

Spring 서버 실행 후 브라우저에서 `http://localhost:8080` 또는 `http://localhost:8080/index.html`로 접속

(자동 매칭 스케줄링 Demo는 `http://localhost:8080/matching.html`로 접속)