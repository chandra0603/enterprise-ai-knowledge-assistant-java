package com.knowledge.assistant.enterpriseaiknowledgeassistant.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ingested_document")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IngestedDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String fileName;

    @Column(nullable = false)
    private String checksum;

    @Column(nullable = false)
    private LocalDateTime uploadedAt;

}