package com.knowledge.assistant.enterpriseaiknowledgeassistant.controller;

import com.knowledge.assistant.enterpriseaiknowledgeassistant.service.UploadService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadController {

    private final UploadService uploadService;

    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @PostMapping
    public String upload(@RequestParam("file") MultipartFile file) {

        try {

            uploadService.upload(file);

            return "Uploaded Successfully";

        } catch (Exception e) {

            e.printStackTrace();   // <-- prints full error in IntelliJ console

            throw new RuntimeException(e);
        }
    }
}