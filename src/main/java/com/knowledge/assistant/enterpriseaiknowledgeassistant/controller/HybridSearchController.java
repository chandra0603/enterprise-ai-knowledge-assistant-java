package com.knowledge.assistant.enterpriseaiknowledgeassistant.controller;

import com.knowledge.assistant.enterpriseaiknowledgeassistant.service.HybridSearchService;
import org.springframework.ai.document.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hybrid")
public class HybridSearchController {

    private final HybridSearchService hybridSearchService;

    public HybridSearchController(HybridSearchService hybridSearchService) {
        this.hybridSearchService = hybridSearchService;
    }

    @GetMapping("/search")
    public List<Document> search(
            @RequestParam String query,
            @RequestParam(required = false) String category) {

        return hybridSearchService.search(query, category);
    }
}