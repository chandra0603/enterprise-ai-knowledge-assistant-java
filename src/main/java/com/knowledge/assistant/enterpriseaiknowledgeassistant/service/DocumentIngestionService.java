package com.knowledge.assistant.enterpriseaiknowledgeassistant.service;

import com.knowledge.assistant.enterpriseaiknowledgeassistant.util.FileChecksumUtil;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DocumentIngestionService {

    private final VectorStore vectorStore;
    private final DocumentLoaderService loaderService;
    private final ChunkingService chunkingService;
    private final IngestionHistoryService historyService;
    private final FileChecksumUtil checksumUtil;

    public DocumentIngestionService(
            VectorStore vectorStore,
            DocumentLoaderService loaderService,
            ChunkingService chunkingService, IngestionHistoryService historyService, FileChecksumUtil checksumUtil) {

        this.vectorStore = vectorStore;
        this.loaderService = loaderService;
        this.chunkingService = chunkingService;
        this.historyService = historyService;
        this.checksumUtil = checksumUtil;
    }

    public void ingest(Path filePath)
            throws IOException, NoSuchAlgorithmException {

        List<Document> documents =
                loaderService.load(filePath);

        String checksum =
                checksumUtil.calculate(filePath);

        for (Document document : documents) {

            document.getMetadata().put("checksum", checksum);
        }

        List<Document> chunks =
                chunkingService.split(documents);

        vectorStore.add(chunks);

        Map<String, String> processedFiles = new HashMap<>();

        for (Document chunk : chunks) {

            String fileName =
                    chunk.getMetadata().get("fileName").toString();

            String fileChecksum =
                    chunk.getMetadata().get("checksum").toString();

            processedFiles.put(fileName, fileChecksum);
        }

        processedFiles.forEach(historyService::saveOrUpdate);

        System.out.println("Documents : " + documents.size());
        System.out.println("Chunks : " + chunks.size());
    }

}