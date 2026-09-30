package com.knowledge.assistant.enterpriseaiknowledgeassistant.service;

import com.knowledge.assistant.enterpriseaiknowledgeassistant.config.StorageProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

    private final StorageProperties storageProperties;

    public FileStorageService(StorageProperties storageProperties) {

        this.storageProperties = storageProperties;

        System.out.println("Upload Path = " + storageProperties.getUploadPath());
    }

    public String save(MultipartFile file) throws IOException {

        Path uploadPath =
                Paths.get(storageProperties.getUploadPath());

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Path filePath =
                uploadPath.resolve(file.getOriginalFilename());

        Files.copy(
                file.getInputStream(),
                filePath,
                StandardCopyOption.REPLACE_EXISTING
        );

        return filePath.toString();
    }

}