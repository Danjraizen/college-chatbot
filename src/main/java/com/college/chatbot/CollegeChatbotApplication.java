package com.college.chatbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 🎓 COLLEGE AI CHATBOT - Main Application
 * 
 * PURPOSE:
 * This is a demo project showcasing how AI is integrated and controlled 
 * inside a real Java backend. The chatbot responds conversationally to 
 * college-related questions while Java enforces strict rules.
 * 
 * ARCHITECTURE:
 * Client (Browser/Postman) 
 *    → Spring Boot REST API 
 *    → Prompt + Rules (Java) 
 *    → Gemini API (LLM) 
 *    → Conversational Response
 * 
 * CORE PRINCIPLE:
 * "AI speaks like a human, but engineers decide what it's allowed to talk about."
 * 
 * KEY FEATURES:
 * ✅ Stateless design (no session/database)
 * ✅ Conversational responses
 * ✅ Scope enforcement (college-only topics)
 * ✅ Fallback handling
 * ✅ Production-ready patterns
 * 
 * @author Demo Project for Technical Talk
 * @version 1.0
 */
@SpringBootApplication
public class CollegeChatbotApplication {

    public static void main(String[] args) {
        SpringApplication.run(CollegeChatbotApplication.class, args);
        
        System.out.println("\n" +
                "╔═══════════════════════════════════════════════════════════╗\n" +
                "║       🎓 COLLEGE AI CHATBOT - Ready to Serve! 🤖         ║\n" +
                "╠═══════════════════════════════════════════════════════════╣\n" +
                "║  API Endpoint: POST http://localhost:8080/api/chat        ║\n" +
                "║  Frontend: http://localhost:8080                          ║\n" +
                "║                                                           ║\n" +
                "║  Demo this project by asking:                            ║\n" +
                "║    ✅ \"What courses are offered?\"                        ║\n" +
                "║    ✅ \"How are the placements?\"                          ║\n" +
                "║    ❌ \"Who will win IPL?\" (to see refusal)               ║\n" +
                "╚═══════════════════════════════════════════════════════════╝\n");
    }
}
