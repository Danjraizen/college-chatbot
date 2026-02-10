package com.college.chatbot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 📥 CHAT REQUEST DTO
 * 
 * Data Transfer Object for incoming chat requests.
 * 
 * VALIDATION RULES:
 * - Question cannot be empty/blank
 * - Question must be at most 300 characters
 * 
 * EXAMPLE JSON:
 * {
 *   "question": "What courses are offered?"
 * }
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRequest {
    
    @NotBlank(message = "Question cannot be empty")
    @Size(max = 300, message = "Question must be at most 300 characters")
    private String question;
}
