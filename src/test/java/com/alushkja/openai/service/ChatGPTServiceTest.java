package com.alushkja.openai.service;

import com.alushkja.openai.model.chatgpt.chat.ChatCompletionChoice;
import com.alushkja.openai.model.chatgpt.chat.ChatCompletionResponse;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class ChatGPTServiceTest {
    @Inject
    ChatGPTService chatGPTService;

    @Test
    void chatCompletion() {
        ChatCompletionResponse response = chatGPTService.chatCompletion("Hello, thank for responding!");
        assertNotNull(response);
        assertNotNull(response.getChoices());
        for (ChatCompletionChoice choice : response.getChoices()) {
            assertNotNull(choice.getMessage());
        }
    }
}