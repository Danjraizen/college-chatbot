package com.college.chatbot.controller;

import com.college.chatbot.dto.ChatRequest;
import com.college.chatbot.dto.ChatResponse;
import com.college.chatbot.service.ChatService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 🌐 CHAT CONTROLLER - REST API Layer
 * 
 * PURPOSE:
 * Exposes the chatbot functionality via HTTP REST API.
 * 
 * ENDPOINT:
 * POST /api/chat
 * 
 * REQUEST:
 * {
 *   "question": "What courses are offered?"
 * }
 * 
 * RESPONSE:
 * {
 *   "answer": "Sure 🙂 ABC College offers programs like B.E, B.Tech, and MCA..."
 * }
 * 
 * RESPONSIBILITIES:
 * - Handle HTTP requests
 * - Validate request format
 * - Delegate to ChatService
 * - Return HTTP responses
 * 
 * @author Demo Project for Technical Talk
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Allow frontend to call this API
public class ChatController {

    private final ChatService chatService;

    // Dependency injection
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    /**
     * Chat endpoint - the main entry point for chatbot interactions.
     * 
     * HOW TO TEST:
     * 
     * Using curl:
     * curl -X POST http://localhost:8080/api/chat \
     *   -H "Content-Type: application/json" \
     *   -d '{"question": "What courses are offered?"}'
     * 
     * Using Postman:
     * - Method: POST
     * - URL: http://localhost:8080/api/chat
     * - Headers: Content-Type = application/json
     * - Body (raw JSON): {"question": "What courses are offered?"}
     * 
     * @param request Chat request containing the question
     * @return Chat response with AI-generated answer
     */
    @PostMapping("/chat")
    public ResponseEntity<ChatResponse> chat(@Valid @RequestBody ChatRequest request) {
        
        // Process the question via ChatService
        String answer = chatService.processQuestion(request.getQuestion());
        
        // Build and return response
        ChatResponse response = new ChatResponse(answer);
        return ResponseEntity.ok(response);
    }

    /**
     * Health check endpoint - useful for verifying the API is running.
     * 
     * GET /api/health
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("🎓 College Chatbot API is running! Ready to help students. 🤖");
    }
}
