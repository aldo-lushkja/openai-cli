package com.alushkja.openai.command;

import com.alushkja.openai.service.OpenAiService;
import io.quarkus.logging.Log;
import picocli.CommandLine;

@CommandLine.Command(name = "openai-cli")
public class OpenAiCommand implements Runnable{

    @CommandLine.Option(names = {"-q", "--query"}, description = "OpenAI query input text", defaultValue = "Hello, World!")
    String query;

    private final OpenAiService openAiService;

    public OpenAiCommand(OpenAiService openAiService) {
        this.openAiService = openAiService;
    }

    @Override
    public void run() {
        Log.info("Got message 👉: " + openAiService.chatCompletion(query));
    }
}
