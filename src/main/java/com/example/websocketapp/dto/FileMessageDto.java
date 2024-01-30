package com.example.websocketapp.dto;

import com.example.websocketapp.domain.MessageType;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileMessageDto {

    private MultipartFile file;
    private Long roomId;
    private Long senderId;
    private MessageType type;
}
