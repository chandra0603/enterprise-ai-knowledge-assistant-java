package com.knowledge.assistant.enterpriseaiknowledgeassistant.service;

import com.knowledge.assistant.enterpriseaiknowledgeassistant.ai.prompt.RagPromptBuilder;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.document.Document;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RagService {

    private final RetrievalService retrievalService;
    private final ChatClient chatClient;

    public RagService(RetrievalService retrievalService,
                      ChatClient chatClient) {

        this.retrievalService = retrievalService;
        this.chatClient = chatClient;
    }

    public String ask(String conversationId,
                      String question,
                      String category) {

        List<Document> documents =
                retrievalService.search(question, category);

        String prompt = RagPromptBuilder.build(question, documents);

        return chatClient.prompt()
                .user(prompt)
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationId))
                .call()
                .content();
    }
}