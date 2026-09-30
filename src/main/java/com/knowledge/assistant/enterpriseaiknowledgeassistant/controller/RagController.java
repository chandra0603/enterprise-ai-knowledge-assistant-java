package com.knowledge.assistant.enterpriseaiknowledgeassistant.controller;

import com.knowledge.assistant.enterpriseaiknowledgeassistant.dto.RagRequest;
import com.knowledge.assistant.enterpriseaiknowledgeassistant.service.RagService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rag")
public class RagController {

    private final RagService ragService;

    public RagController(RagService ragService) {
        this.ragService = ragService;
    }

    @PostMapping
    public String ask(@RequestBody RagRequest request) {

        return ragService.ask(
                request.getConversationId(),
                request.getQuestion(),
                request.getCategory()
        );
    }
}