package com.example.websocketapp.controller;

import com.example.websocketapp.domain.ChatMessage;
import com.example.websocketapp.domain.MessageType;
import com.example.websocketapp.dto.ChatMessageDto;
import com.example.websocketapp.dto.FileMessageDto;
import com.example.websocketapp.service.ChatService;
import com.example.websocketapp.service.S3UploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/file")
public class FileUploadController {

    private final ChatService chatService;
    private final S3UploadService s3UploadService;
    private final SimpMessageSendingOperations simpMessageSendingOperations;

    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file, Long roomId, Long senderId, String type) throws Exception {
        log.info("file: {}", file);
        log.info("roomId: {}", roomId);
        log.info("senderId: {}", senderId);
        log.info("type: {}", type);
        if (!file.isEmpty()) {
            String url = s3UploadService.saveFile(file);
            log.info("url: {}", url);
            ChatMessage message = chatService.save(ChatMessageDto.builder()
                    .roomId(roomId)
                    .senderId(senderId)
                    .type(MessageType.valueOf(type))
                    .content(url)
                    .build());
            simpMessageSendingOperations.convertAndSend("/topic/room." + roomId, message);
            return url;
        }
        return "good";
    }
}
