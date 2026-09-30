package com.knowledge.assistant.enterpriseaiknowledgeassistant.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@RestController
public class HealthController {

    @GetMapping("/health")
    public Map<String, Object> health() {

        log.info("Health API called");

        return Map.of(
                "status", "UP",
                "application", "Enterprise AI Knowledge Assistant",
                "timestamp", LocalDateTime.now()
        );
    }

}