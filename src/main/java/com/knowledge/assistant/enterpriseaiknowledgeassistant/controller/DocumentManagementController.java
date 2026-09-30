package com.knowledge.assistant.enterpriseaiknowledgeassistant.controller;

import com.knowledge.assistant.enterpriseaiknowledgeassistant.dto.DocumentResponse;
import com.knowledge.assistant.enterpriseaiknowledgeassistant.service.DocumentManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documents")
@RequiredArgsConstructor
public class DocumentManagementController {

    private final DocumentManagementService documentManagementService;

    @GetMapping
    public List<DocumentResponse> getAllDocuments() {

        return documentManagementService.getAllDocuments();
    }

    @GetMapping("/{id}")
    public DocumentResponse getDocument(@PathVariable Long id) {

        return documentManagementService.getDocument(id);
    }

}