package com.knowledge.assistant.enterpriseaiknowledgeassistant.service;

import org.springframework.ai.document.Document;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReciprocalRankFusionService {

    private static final int K = 60;

    public List<Document> fuse(
            List<Document> vectorResults,
            List<Document> keywordResults) {

        Map<String, Double> scores = new HashMap<>();
        Map<String, Document> documents = new HashMap<>();

        addScores(vectorResults, scores, documents);
        addScores(keywordResults, scores, documents);

        return scores.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .map(entry -> documents.get(entry.getKey()))
                .toList();
    }

    private void addScores(
            List<Document> results,
            Map<String, Double> scores,
            Map<String, Document> documents) {

        for (int rank = 0; rank < results.size(); rank++) {

            Document document = results.get(rank);

            String key = document.getText();

            double score = 1.0 / (K + rank + 1);

            scores.merge(key, score, Double::sum);

            documents.putIfAbsent(key, document);
        }
    }
}