package com.knowledge.assistant.enterpriseaiknowledgeassistant.service;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RetrievalService {

    private final VectorStore vectorStore;

    public RetrievalService(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    public List<Document> search(String query, String category) {

        SearchRequest.Builder builder = SearchRequest.builder()
                .query(query)
                .topK(5)
                .similarityThreshold(0.75);

        if (category != null && !category.isBlank()) {
            builder.filterExpression("category == '" + category + "'");
        }

        return vectorStore.similaritySearch(builder.build());
    }
}