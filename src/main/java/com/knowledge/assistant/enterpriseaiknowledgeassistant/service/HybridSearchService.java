package com.knowledge.assistant.enterpriseaiknowledgeassistant.service;

import org.springframework.ai.document.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class HybridSearchService {

    private final RetrievalService retrievalService;
    private final KeywordSearchService keywordSearchService;
    private final ReciprocalRankFusionService fusionService;

    public HybridSearchService(
            RetrievalService retrievalService,
            KeywordSearchService keywordSearchService, ReciprocalRankFusionService fusionService) {

        this.retrievalService = retrievalService;
        this.keywordSearchService = keywordSearchService;
        this.fusionService = fusionService;
    }

    public List<Document> search(String query, String category) {

        List<Document> vectorResults =
                retrievalService.search(query, category);

        List<Document> keywordResults =
                keywordSearchService.search(query);

        return fusionService.fuse(
                vectorResults,
                keywordResults
        );
    }

}