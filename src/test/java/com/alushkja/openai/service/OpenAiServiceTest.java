package com.alushkja.openai.service;

import com.alushkja.openai.model.ChatCompletionChoice;
import com.alushkja.openai.model.ChatCompletionResponse;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
class OpenAiServiceTest {
    @Inject
    OpenAiService openAiService;

    @Test
    void chatCompletion() {
        ChatCompletionResponse response = openAiService.chatCompletion("Hello, thank for responding!");
        assertNotNull(response);
        assertNotNull(response.getChoices());
        for (ChatCompletionChoice choice : response.getChoices()) {
            assertNotNull(choice.getMessage());
        }
    }
}