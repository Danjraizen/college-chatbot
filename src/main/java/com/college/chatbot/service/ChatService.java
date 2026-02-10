package com.college.chatbot.service;

import com.college.chatbot.client.GeminiClient;
import org.springframework.stereotype.Service;

/**
 * 💼 CHAT SERVICE - Business Logic Layer
 * 
 * PURPOSE:
 * This is where ENGINEERING CONTROL happens.
 * 
 * RESPONSIBILITIES:
 * 1. Input validation (guardrails before AI)
 * 2. Orchestrating GeminiClient
 * 3. Error handling and fallback
 * 4. Business logic enforcement
 * 
 * WHY THIS LAYER EXISTS:
 * We don't trust the AI blindly. This service ensures:
 * - Invalid inputs never reach the AI
 * - API failures are handled gracefully
 * - Responses are controlled and consistent
 * 
 * STATELESS DESIGN:
 * Notice: No database, no session storage.
 * Each request is independent.
 * 
 * @author Demo Project for Technical Talk
 */
@Service
public class ChatService {

    private final GeminiClient geminiClient;

    // Dependency injection - Spring will provide the GeminiClient
    public ChatService(GeminiClient geminiClient) {
        this.geminiClient = geminiClient;
    }

    /**
     * Processes a chat question and returns an AI-generated answer.
     * 
     * FLOW:
     * 1. Validate input (check empty, length, etc.)
     * 2. Call Gemini API via GeminiClient
     * 3. Return response
     * 4. If anything fails, return fallback message
     * 
     * @param question User's question
     * @return Conversational answer
     */
    public String processQuestion(String question) {
        
        // GUARDRAIL 1: Validate input
        String validationError = validateInput(question);
        System.out.println("validationError "+ validationError);
        if (validationError != null) {
            return validationError;
        }

        try {
            // Call Gemini API
            System.out.println("question.trim() "+ question.trim());
            String response = geminiClient.generateResponse(question.trim());
            return response;
            
        } catch (Exception e) {
            // FALLBACK: If AI fails, return friendly error message
            // In production, you'd log this error
            e.printStackTrace();
            System.err.println("Error calling Gemini API: " + e.getMessage());
            return "Sorry 😕 the system is temporarily unavailable. Please try again later.";
        }
    }

    /**
     * Validates user input before sending to AI.
     * 
     * RULES:
     * - Cannot be null or empty
     * - Cannot exceed 300 characters
     * - Must contain actual text (not just whitespace)
     * 
     * @param question User's question
     * @return Error message if invalid, null if valid
     */
    private String validateInput(String question) {
        
        // Check null or empty
        if (question == null || question.trim().isEmpty()) {
            return "Please ask a question 🙂";
        }

        // Check length (already enforced by @Size annotation, but double-checking)
        if (question.length() > 300) {
            return "Question is too long. Please keep it under 300 characters 😊";
        }

        // Input is valid
        return null;
    }
}
