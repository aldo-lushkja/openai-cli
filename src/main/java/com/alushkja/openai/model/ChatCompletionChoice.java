package com.alushkja.openai.model;

public class ChatCompletionChoice {

    private Integer index;
    private ChatCompletionMessage message;
    private String finishReason;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public ChatCompletionMessage getMessage() {
        return message;
    }

    public void setMessage(ChatCompletionMessage message) {
        this.message = message;
    }

    public String getFinishReason() {
        return finishReason;
    }

    public void setFinishReason(String finishReason) {
        this.finishReason = finishReason;
    }
}
