package com.knowledge.assistant.enterpriseaiknowledgeassistant.service;

import com.knowledge.assistant.enterpriseaiknowledgeassistant.dto.KeywordSearchResult;
import com.knowledge.assistant.enterpriseaiknowledgeassistant.repository.KeywordSearchRepository;
import org.springframework.ai.document.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KeywordSearchService {

    private final KeywordSearchRepository repository;

    public KeywordSearchService(KeywordSearchRepository repository) {
        this.repository = repository;
    }

    public List<Document> search(String query) {

        List<KeywordSearchResult> results =
                repository.search(query);

        List<Document> documents =
                new ArrayList<>();

        for (KeywordSearchResult result : results) {

            Document document =
                    new Document(result.content());

            document.getMetadata().put(
                    "category",
                    result.category()
            );

            document.getMetadata().put(
                    "fileName",
                    result.fileName()
            );

            documents.add(document);
        }

        return documents;
    }
}