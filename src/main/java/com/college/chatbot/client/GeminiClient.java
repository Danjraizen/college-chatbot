package com.college.chatbot.client;

import com.college.chatbot.config.PromptConfig;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 🤖 GEMINI API CLIENT
 * 
 * PURPOSE:
 * Handles direct communication with Google Gemini REST API.
 * This is the layer that talks to the actual AI model.
 * 
 * RESPONSIBILITIES:
 * - Construct API requests (combine system prompt + user question)
 * - Send HTTP requests to Gemini
 * - Parse AI responses
 * - Handle API errors
 * 
 * ARCHITECTURE PATTERN:
 * This class is ISOLATED from business logic. It only knows how to:
 * 1. Call Gemini API
 * 2. Return the response
 * 
 * The business rules are in ChatService, not here.
 * 
 * @author Demo Project for Technical Talk
 */
@Component
public class GeminiClient {

    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final PromptConfig promptConfig;

    public GeminiClient(PromptConfig promptConfig) {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
        this.promptConfig = promptConfig;
    }

    /**
     * Generates a conversational response from Gemini API.
     * 
     * HOW IT WORKS:
     * 1. Combines system prompt (from PromptConfig) + user question
     * 2. Builds JSON request for Gemini API
     * 3. Sends POST request
     * 4. Extracts text from response
     * 
     * @param userQuestion The question asked by the user
     * @return AI-generated response
     * @throws Exception if API call fails
     */
    public String generateResponse(String userQuestion) throws Exception {
        
        // Step 1: Combine system prompt + user question
        // This is where Java injects the rules into the AI
        String fullPrompt = promptConfig.getSystemPrompt() + "\n\nUser Question: " + userQuestion;
        
        // Step 2: Build request body for Gemini API
        Map<String, Object> requestBody = buildRequestBody(fullPrompt);
        
        // Step 3: Set up HTTP headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        // Step 4: Create HTTP entity
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
        
        // Step 5: Make API call with timeout handling
        String url = apiUrl + "?key=" + apiKey;
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                String.class
        );
        
        // Step 6: Extract text from response
        return parseResponse(response.getBody());
    }

    /**
     * Builds the request body in Gemini API format.
     * 
     * Expected format:
     * {
     *   "contents": [{
     *     "parts": [{"text": "prompt here"}]
     *   }]
     * }
     */
    private Map<String, Object> buildRequestBody(String prompt) {
        Map<String, Object> textPart = new HashMap<>();
        textPart.put("text", prompt);

        Map<String, Object> content = new HashMap<>();
        content.put("parts", List.of(textPart));

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("contents", List.of(content));

        return requestBody;
    }

    /**
     * Parses the response from Gemini API.
     * 
     * The response format is:
     * {
     *   "candidates": [{
     *     "content": {
     *       "parts": [{"text": "AI response here"}]
     *     }
     *   }]
     * }
     * 
     * We extract the "text" field from the first candidate.
     */
    private String parseResponse(String responseBody) throws Exception {
        JsonNode root = objectMapper.readTree(responseBody);
        
        // Navigate: candidates[0].content.parts[0].text
        JsonNode candidates = root.path("candidates");
        if (candidates.isEmpty()) {
            throw new Exception("No response candidates from Gemini API");
        }
        
        JsonNode firstCandidate = candidates.get(0);
        JsonNode content = firstCandidate.path("content");
        JsonNode parts = content.path("parts");
        
        if (parts.isEmpty()) {
            throw new Exception("No content parts in Gemini response");
        }
        
        JsonNode textNode = parts.get(0).path("text");
        return textNode.asText();
    }
}
