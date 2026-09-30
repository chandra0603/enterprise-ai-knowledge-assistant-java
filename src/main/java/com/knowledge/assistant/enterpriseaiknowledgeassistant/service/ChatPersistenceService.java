package com.knowledge.assistant.enterpriseaiknowledgeassistant.service;

import com.knowledge.assistant.enterpriseaiknowledgeassistant.entity.ChatMessage;
import com.knowledge.assistant.enterpriseaiknowledgeassistant.entity.ChatRole;
import com.knowledge.assistant.enterpriseaiknowledgeassistant.entity.ChatSession;
import com.knowledge.assistant.enterpriseaiknowledgeassistant.repository.ChatMessageRepository;
import com.knowledge.assistant.enterpriseaiknowledgeassistant.repository.ChatSessionRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ChatPersistenceService {

    private final ChatSessionRepository chatSessionRepository;
    private final ChatMessageRepository chatMessageRepository;

    public ChatPersistenceService(ChatSessionRepository chatSessionRepository,
                                  ChatMessageRepository chatMessageRepository) {

        this.chatSessionRepository = chatSessionRepository;
        this.chatMessageRepository = chatMessageRepository;
    }

    public ChatSession getOrCreateSession(String conversationId) {

        return chatSessionRepository
                .findByConversationId(conversationId)
                .orElseGet(() -> {

                    ChatSession session = new ChatSession();
                    session.setConversationId(conversationId);

                    return chatSessionRepository.save(session);
                });
        }

    private void saveMessage(ChatSession session,
                             ChatRole role,
                             String message) {

        ChatMessage chatMessage = new ChatMessage();

        chatMessage.setSession(session);
        chatMessage.setRole(role);
        chatMessage.setMessage(message);

        chatMessageRepository.save(chatMessage);
    }

    public void saveUserMessage(ChatSession session,
                                String message) {

        saveMessage(session,
                ChatRole.USER,
                message);
    }

    public void saveAssistantMessage(ChatSession session,
                                     String message) {

        saveMessage(session,
                ChatRole.ASSISTANT,
                message);
    }

    public List<ChatMessage> loadConversationHistory(String conversationId) {

        ChatSession session = chatSessionRepository
                .findByConversationId(conversationId)
                .orElse(null);

        if (session == null) {
            return Collections.emptyList();
        }

        return chatMessageRepository.findBySessionOrderByCreatedAtAsc(session);
    }
}