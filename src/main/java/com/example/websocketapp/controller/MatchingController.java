package com.example.websocketapp.controller;

import com.example.websocketapp.store.MatchingQueueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/matching")
@RequiredArgsConstructor
public class MatchingController {

    private final MatchingQueueService matchingQueueService;

    @RequestMapping("/users")
    public List<Object> getAllUsers() {
        return matchingQueueService.getAllUsers();
    }
}
