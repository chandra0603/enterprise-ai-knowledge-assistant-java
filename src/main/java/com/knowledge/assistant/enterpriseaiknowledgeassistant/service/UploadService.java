package com.knowledge.assistant.enterpriseaiknowledgeassistant.service;

import com.knowledge.assistant.enterpriseaiknowledgeassistant.service.DocumentIngestionService;
import com.knowledge.assistant.enterpriseaiknowledgeassistant.util.FileChecksumUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.knowledge.assistant.enterpriseaiknowledgeassistant.service.FileStorageService;

import java.nio.file.Path;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;

@Service
public class UploadService {

    private final FileStorageService storageService;
    private final DocumentIngestionService ingestionService;

    public UploadService(
            FileStorageService storageService,
            DocumentIngestionService ingestionService) {

        this.storageService = storageService;
        this.ingestionService = ingestionService;
    }

    public void upload(MultipartFile file) throws Exception {

//        String path = storageService.save(file);

        String path = storageService.save(file);

        ingestionService.ingest(Path.of(path));
    }
}