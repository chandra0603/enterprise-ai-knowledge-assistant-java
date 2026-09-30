package com.knowledge.assistant.enterpriseaiknowledgeassistant.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentResponse {

    private Long id;

    private String fileName;

    private String checksum;

    private LocalDateTime uploadedAt;

}