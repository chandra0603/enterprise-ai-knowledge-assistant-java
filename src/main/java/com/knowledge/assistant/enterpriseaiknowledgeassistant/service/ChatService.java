package com.knowledge.assistant.enterpriseaiknowledgeassistant.service;

import com.knowledge.assistant.enterpriseaiknowledgeassistant.ai.prompt.PromptBuilder;
import com.knowledge.assistant.enterpriseaiknowledgeassistant.ai.prompt.PromptTemplates;
import com.knowledge.assistant.enterpriseaiknowledgeassistant.entity.ChatMessage;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class ChatService {

    private final ChatClient chatClient;
    private final ChatPersistenceService persistenceService;
    private final PromptBuilder promptBuilder;

    public ChatService(ChatClient chatClient,
                       ChatPersistenceService persistenceService,
                       PromptBuilder promptBuilder) {

        this.chatClient = chatClient;
        this.persistenceService = persistenceService;
        this.promptBuilder = promptBuilder;
    }

    public String chat(String conversationId, String userMessage) {

        // Step 1 : Get/Create Session
        com.knowledge.assistant.enterpriseaiknowledgeassistant.entity.ChatSession session =
                persistenceService.getOrCreateSession(conversationId);

        // Step 2 : Load History
        List<ChatMessage> history =
                persistenceService.loadConversationHistory(conversationId);

        // Step 3 : Build Prompt
        List<Message> messages =
                promptBuilder.buildMessages(history, userMessage);

        // Step 4 : Call Gemini
        String response = chatClient
                .prompt(messages.toString())
                .call()
                .content();

        // Step 5 : Save User Message
        persistenceService.saveUserMessage(session, userMessage);

        // Step 6 : Save AI Response
        persistenceService.saveAssistantMessage(session, response);

        return response;
    }

    public Flux<String> streamChat(String message) {

        return chatClient
                .prompt()
                .system(PromptTemplates.JAVA_ASSISTANT)
                .user(message)
                .stream()
                .content();

    }
}