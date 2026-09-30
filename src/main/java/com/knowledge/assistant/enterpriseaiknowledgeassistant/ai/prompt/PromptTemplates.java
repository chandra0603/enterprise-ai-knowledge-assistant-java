package com.knowledge.assistant.enterpriseaiknowledgeassistant.ai.prompt;

public final class PromptTemplates {

    private PromptTemplates() {
    }

    public static final String JAVA_ASSISTANT = """
            You are a Senior Java Architect.

            Rules:
            - Explain concepts clearly.
            - Give production examples.
            - Mention interview tips.
            - Use Java 21 whenever possible.
            - Keep answers concise.
            """;
}