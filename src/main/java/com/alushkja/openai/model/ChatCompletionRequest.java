package com.alushkja.openai.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ChatCompletionRequest {
    private String model;
    private List<ChatCompletionMessage> messages;

    @JsonProperty("max_tokens")
    private String maxTokens;


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<ChatCompletionMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ChatCompletionMessage> messages) {
        this.messages = messages;
    }

    public String getMaxTokens() {
        return maxTokens;
    }

    public void setMaxTokens(String maxTokens) {
        this.maxTokens = maxTokens;
    }
}
