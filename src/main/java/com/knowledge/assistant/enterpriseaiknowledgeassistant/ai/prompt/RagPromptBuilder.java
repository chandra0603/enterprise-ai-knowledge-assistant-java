package com.knowledge.assistant.enterpriseaiknowledgeassistant.ai.prompt;

import org.springframework.ai.document.Document;

import java.util.List;

public final class RagPromptBuilder {

    private RagPromptBuilder() {
    }

    public static String build(String question, List<Document> documents) {

        StringBuilder context = new StringBuilder();

        int index = 1;

        for (Document document : documents) {

            context.append("""
                    Source %d
                    ----------------------
                    %s

                    """.formatted(index++, document.getText()));
        }

        return """
                You are an Enterprise Java AI Assistant.

                Answer ONLY from the provided context.

                If the answer is not available, say:

                "I don't know based on the provided documents."

                Mention which Source number you used.

                Context:

                %s

                Question:

                %s
                """.formatted(context, question);
    }
}