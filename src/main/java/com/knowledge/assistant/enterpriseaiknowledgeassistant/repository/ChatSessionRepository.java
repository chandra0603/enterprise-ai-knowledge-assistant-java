package com.knowledge.assistant.enterpriseaiknowledgeassistant.repository;

import com.knowledge.assistant.enterpriseaiknowledgeassistant.entity.ChatSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatSessionRepository
        extends JpaRepository<ChatSession, Long> {

    Optional<ChatSession> findByConversationId(String conversationId);

}