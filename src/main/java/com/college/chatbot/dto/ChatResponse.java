package com.college.chatbot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 📤 CHAT RESPONSE DTO
 * 
 * Data Transfer Object for outgoing chat responses.
 * 
 * Contains the AI-generated answer in a conversational format.
 * 
 * EXAMPLE JSON:
 * {
 *   "answer": "Sure 🙂 ABC College offers programs like B.E, B.Tech, and MCA. 
 *              Would you like details about any specific course?"
 * }
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatResponse {
    
    private String answer;
}
