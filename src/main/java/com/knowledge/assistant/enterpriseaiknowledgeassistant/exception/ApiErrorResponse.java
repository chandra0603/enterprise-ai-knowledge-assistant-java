package com.knowledge.assistant.enterpriseaiknowledgeassistant.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ApiErrorResponse {

    private String timestamp;

    private int status;

    private String error;

    private String message;

    private String path;

}