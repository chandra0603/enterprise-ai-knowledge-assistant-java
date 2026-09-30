package com.knowledge.assistant.enterpriseaiknowledgeassistant.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RagRequest {

    private String conversationId;
    private String question;
    private String category;

}