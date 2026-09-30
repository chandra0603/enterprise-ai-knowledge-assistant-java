package com.knowledge.assistant.enterpriseaiknowledgeassistant.service;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.List;

@Service
public class DocumentLoaderService {

    public List<Document> load(Path filePath) {

        PagePdfDocumentReader reader =
                new PagePdfDocumentReader(
                        new FileSystemResource(filePath)
                );

        List<Document> documents = reader.get();

        String fileName = filePath.getFileName().toString();
        String category = fileName.replace(".pdf", "");

        for (Document document : documents) {

            document.getMetadata().put("fileName", fileName);
            document.getMetadata().put("category", category);
            document.getMetadata().put("source", fileName);
            document.getMetadata().put("uploadedBy", "admin");

        }

        return documents;
    }

}