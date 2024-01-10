package com.alushkja.openai.model.chatgpt.chat;

//convert to java record
public class ChatCompletionResponse {
    private String id;
    private String object;
    private int created;
    private String model;
    private ChatCompletionChoice[] choices;
    private ChatCompletionUsage usage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public ChatCompletionChoice[] getChoices() {
        return choices;
    }

    public void setChoices(ChatCompletionChoice[] choices) {
        this.choices = choices;
    }

    public ChatCompletionUsage getUsage() {
        return usage;
    }

    public void setUsage(ChatCompletionUsage usage) {
        this.usage = usage;
    }
}


/**
 * {
 *   "id": "chatcmpl-123",
 *   "object": "chat.completion",
 *   "created": 1677652288,
 *   "model": "gpt-3.5-turbo-0613",
 *   "system_fingerprint": "fp_44709d6fcb",
 *   "choices": [{
 *     "index": 0,
 *     "message": {
 *       "role": "assistant",
 *       "content": "\n\nHello there, how may I assist you today?",
 *     },
 *     "logprobs": null,
 *     "finish_reason": "stop"
 *   }],
 *   "usage": {
 *     "prompt_tokens": 9,
 *     "completion_tokens": 12,
 *     "total_tokens": 21
 *   }
 * }
 */