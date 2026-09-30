package com.knowledge.assistant.enterpriseaiknowledgeassistant.controller;

import com.knowledge.assistant.enterpriseaiknowledgeassistant.service.ChatService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import org.springframework.ai.chat.memory.ChatMemory;

@RestController
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam String conversationId, @RequestParam String message) {
        return chatService.chat(conversationId, message);
    }

    @GetMapping(value="/chat/stream",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> stream(@RequestParam String message){

        return chatService.streamChat(message);

    }
}