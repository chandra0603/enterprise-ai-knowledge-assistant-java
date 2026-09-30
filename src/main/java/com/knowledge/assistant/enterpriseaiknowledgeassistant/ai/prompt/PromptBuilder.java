package com.knowledge.assistant.enterpriseaiknowledgeassistant.ai.prompt;

import com.knowledge.assistant.enterpriseaiknowledgeassistant.entity.ChatMessage;
import com.knowledge.assistant.enterpriseaiknowledgeassistant.entity.ChatRole;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PromptBuilder {

    public List<Message> buildMessages(List<ChatMessage> history,
                                       String currentMessage) {

        List<Message> messages = new ArrayList<>();

        messages.add(
                new SystemMessage(PromptTemplates.JAVA_ASSISTANT)
        );

        for (ChatMessage chatMessage : history) {

            if (chatMessage.getRole() == ChatRole.USER) {

                messages.add(
                        new UserMessage(chatMessage.getMessage())
                );

            } else {

                messages.add(
                        new AssistantMessage(chatMessage.getMessage())
                );
            }

        }

        messages.add(
                new UserMessage(currentMessage)
        );

        return messages;
    }

}