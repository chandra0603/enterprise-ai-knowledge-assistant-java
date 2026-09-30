package com.knowledge.assistant.enterpriseaiknowledgeassistant.service;

import com.knowledge.assistant.enterpriseaiknowledgeassistant.entity.IngestedDocument;
import com.knowledge.assistant.enterpriseaiknowledgeassistant.repository.IngestedDocumentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class IngestionHistoryService {

    private final IngestedDocumentRepository repository;

    public IngestionHistoryService(IngestedDocumentRepository repository) {
        this.repository = repository;
    }

    public boolean alreadyExists(String fileName) {
        return repository.findByFileName(fileName).isPresent();
    }

    public Optional<IngestedDocument> findByFileName(String fileName) {
        return repository.findByFileName(fileName);
    }

    public void save(String fileName, String checksum) {

        IngestedDocument document = IngestedDocument.builder()
                .fileName(fileName)
                .checksum(checksum)
                .uploadedAt(LocalDateTime.now())
                .build();

        repository.save(document);
    }

    public void delete(String fileName) {
        repository.findByFileName(fileName)
                .ifPresent(repository::delete);
    }

    public void saveOrUpdate(String fileName, String checksum) {

        Optional<IngestedDocument> existing =
                repository.findByFileName(fileName);

        if (existing.isPresent()) {

            IngestedDocument document = existing.get();

            document.setChecksum(checksum);
            document.setUploadedAt(LocalDateTime.now());

            repository.save(document);

        } else {

            repository.save(
                    IngestedDocument.builder()
                            .fileName(fileName)
                            .checksum(checksum)
                            .uploadedAt(LocalDateTime.now())
                            .build()
            );

        }
    }

    public boolean alreadyIndexed(String fileName, String checksum) {

        return repository.findByFileName(fileName)
                .map(doc -> doc.getChecksum().equals(checksum))
                .orElse(false);
    }
}