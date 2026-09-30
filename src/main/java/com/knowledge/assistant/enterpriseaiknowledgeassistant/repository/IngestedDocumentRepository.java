package com.knowledge.assistant.enterpriseaiknowledgeassistant.repository;

import com.knowledge.assistant.enterpriseaiknowledgeassistant.entity.IngestedDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IngestedDocumentRepository
        extends JpaRepository<IngestedDocument, Long> {

    Optional<IngestedDocument> findByFileName(String fileName);

}