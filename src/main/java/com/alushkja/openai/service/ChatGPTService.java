package com.alushkja.openai.service;

import com.alushkja.openai.config.AppConfig;
import com.alushkja.openai.model.chatgpt.chat.ChatCompletionMessage;
import com.alushkja.openai.model.chatgpt.chat.ChatCompletionRequest;
import com.alushkja.openai.model.chatgpt.chat.ChatCompletionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.util.List;

@ApplicationScoped
public class ChatGPTService {

    @Inject
    AppConfig appConfig;

    @Inject
    ObjectMapper objectMapper;

    // Create a connection pool with a maximum of 10 connections per route
    CloseableHttpClient httpClient = HttpClients.custom().setMaxConnPerRoute(10).build();

    public ChatCompletionResponse chatCompletion(String prompt) {

        try {
            // Define the request configuration with a timeout of 10 seconds
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(10000)
                    .setConnectTimeout(10000)
                    .build();

            String endpoint = appConfig.openaiApiBaseUrl + "/v1/chat/completions";
            System.out.println("endpoint = " + endpoint);
            HttpPost httpPost = new HttpPost(endpoint);
            httpPost.setConfig(requestConfig);
            // Set the Authorization header
            httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + appConfig.openaiApiKey);

            // Set the Content-Type header for JSON
            httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

            ChatCompletionRequest request = new ChatCompletionRequest();
            request.setModel(appConfig.openaiApiModel);
            request.setMaxTokens(appConfig.openaiApiMaxTokens);
            ChatCompletionMessage message = new ChatCompletionMessage();
            message.setRole("system");
            message.setContent(prompt);
            request.setMessages(List.of(message));

            String serialized = objectMapper.writeValueAsString(request);
            System.out.println("> Request Body: " + serialized);
            httpPost.setEntity(new StringEntity(serialized));

            // Execute the request and get the response
            HttpResponse response = httpClient.execute(httpPost);

            // Print the status code and response body
            System.out.println("Status Code: " + response.getStatusLine().getStatusCode());
            System.out.println("Response Body: " + response.getEntity().getContent());

            String responseSerialized = objectMapper.writeValueAsString(response.getEntity().getContent());

            System.out.println("responseSerialized = " + responseSerialized);


            ChatCompletionResponse chatCompletionResponse = objectMapper.readValue(response.getEntity().getContent(), ChatCompletionResponse.class);
            System.out.println("chatCompletionResponse = " + chatCompletionResponse);
            return chatCompletionResponse;
        } catch (Exception e) {


            e.printStackTrace();
            return null;
        } finally {
            try {
                // Close the HttpClient to release resources
                httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
