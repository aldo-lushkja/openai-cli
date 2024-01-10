package com.alushkja.openai.model.chatgpt.chat;

public class ChatCompletionMessage {
    private String role;
    private String content;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ChatCompletionMessage(String role, String content) {
        this.role = role;
        this.content = content;
    }

    public ChatCompletionMessage() {
    }

    @Override
    public String toString() {
        return "ChatCompletionMessage{" +
                "role='" + role + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public ChatCompletionMessage(String content) {
        this.content = content;
    }

    public ChatCompletionMessage(String role, String content, String content1) {
        this.role = role;
        this.content = content;
    }
}
