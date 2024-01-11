package com.alushkja.openai.service;

import com.alushkja.openai.config.OpenAiConfig;
import com.alushkja.openai.model.ChatCompletionMessage;
import com.alushkja.openai.model.ChatCompletionRequest;
import com.alushkja.openai.model.ChatCompletionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.logging.Log;
import io.quarkus.runtime.util.ExceptionUtil;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.util.List;

@ApplicationScoped
public class OpenAiService {

    // Create a connection pool with a maximum of 10 connections per route
    CloseableHttpClient httpClient = HttpClients.custom().setMaxConnPerRoute(10).build();

    private final ObjectMapper objectMapper;
    private final OpenAiConfig openAiConfig;

    public OpenAiService(ObjectMapper objectMapper, OpenAiConfig openAiConfig) {
        this.objectMapper = objectMapper;
        this.openAiConfig = openAiConfig;
    }

    public ChatCompletionResponse chatCompletion(String prompt) {

        try {
            // Define the request configuration with a timeout of 10 seconds
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(10000)
                    .setConnectTimeout(10000)
                    .build();

            String endpoint = openAiConfig.openaiApiBaseUrl + "/v1/chat/completions";
            Log.info("> Calling endpoint: " + endpoint);
            HttpPost httpPost = new HttpPost(endpoint);
            httpPost.setConfig(requestConfig);
            // Set the Authorization header
            httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + openAiConfig.openaiApiKey);

            // Set the Content-Type header for JSON
            httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

            ChatCompletionRequest request = new ChatCompletionRequest();
            request.setModel(openAiConfig.openaiApiModel);
            request.setMaxTokens(openAiConfig.openaiApiMaxTokens);
            ChatCompletionMessage message = new ChatCompletionMessage();
            message.setRole("system");
            message.setContent(prompt);
            request.setMessages(List.of(message));

            String serialized = objectMapper.writeValueAsString(request);
            Log.info("> Request Body: " + serialized);
            httpPost.setEntity(new StringEntity(serialized));

            // Execute the request and get the response
            HttpResponse response = httpClient.execute(httpPost);

            // Print the status code and response body
            int responseStatusCode = response.getStatusLine().getStatusCode();
            if(responseStatusCode != 200){
                Log.error("< Response status code is not OK: " + responseStatusCode);
                return null;
            }
            String responseSerialized = objectMapper.writeValueAsString(response.getEntity().getContent());
            Log.info("< ResponseBody: " + responseSerialized);
            ChatCompletionResponse chatCompletionResponse = objectMapper.readValue(response.getEntity().getContent(), ChatCompletionResponse.class);
            System.out.println("chatCompletionResponse = " + chatCompletionResponse);
            return chatCompletionResponse;
        } catch (Exception e) {
            Log.error("# An error occurred while sending the request: \n" + ExceptionUtil.generateStackTrace(e));
            return null;
        } finally {
            try {
                // Close the HttpClient to release resources
                httpClient.close();
            } catch (Exception e) {
                Log.error("# An error occurred while trying to close http client request: \n" + ExceptionUtil.generateStackTrace(e));
            }
        }
    }
}
