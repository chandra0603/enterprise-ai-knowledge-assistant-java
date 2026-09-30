package com.knowledge.assistant.enterpriseaiknowledgeassistant.repository;

import com.knowledge.assistant.enterpriseaiknowledgeassistant.entity.ChatMessage;
import com.knowledge.assistant.enterpriseaiknowledgeassistant.entity.ChatSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository
        extends JpaRepository<ChatMessage, Long> {

    List<ChatMessage> findBySessionOrderByCreatedAtAsc(ChatSession session);

}