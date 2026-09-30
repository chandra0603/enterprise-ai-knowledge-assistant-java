package com.knowledge.assistant.enterpriseaiknowledgeassistant.controller;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class EmbeddingController {

    private final EmbeddingModel embeddingModel;

    public EmbeddingController(EmbeddingModel embeddingModel) {
        this.embeddingModel = embeddingModel;
    }

    @GetMapping("/embedding")
    public String embedding() {

        EmbeddingResponse response =
                embeddingModel.embedForResponse(Collections.singletonList("Java Memory Model"));

        return "Vector Dimension : " +
                response.getResult().getOutput().length;
    }
}