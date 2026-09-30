package com.knowledge.assistant.enterpriseaiknowledgeassistant.service;

import com.knowledge.assistant.enterpriseaiknowledgeassistant.dto.DocumentResponse;
import com.knowledge.assistant.enterpriseaiknowledgeassistant.repository.IngestedDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentManagementService {

    private final IngestedDocumentRepository repository;

    public List<DocumentResponse> getAllDocuments() {

        return repository.findAll()
                .stream()
                .map(doc -> DocumentResponse.builder()
                        .id(doc.getId())
                        .fileName(doc.getFileName())
                        .checksum(doc.getChecksum())
                        .uploadedAt(doc.getUploadedAt())
                        .build())
                .toList();
    }

    public DocumentResponse getDocument(Long id) {

        var document = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found"));

        return DocumentResponse.builder()
                .id(document.getId())
                .fileName(document.getFileName())
                .checksum(document.getChecksum())
                .uploadedAt(document.getUploadedAt())
                .build();
    }
}