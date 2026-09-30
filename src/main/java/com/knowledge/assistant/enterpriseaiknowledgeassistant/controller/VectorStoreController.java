package com.knowledge.assistant.enterpriseaiknowledgeassistant.controller;

import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VectorStoreController {

    private final VectorStore vectorStore;

    public VectorStoreController(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @GetMapping("/vector-store")
    public String check() {
        return "VectorStore Bean Created Successfully";
    }
}