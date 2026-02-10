# 🎓 College AI Chatbot

> **A production-style demo showcasing AI integration and control in a Java Spring Boot backend**

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.2-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Gemini API](https://img.shields.io/badge/Google-Gemini%20API-blue.svg)](https://ai.google.dev/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

---

## 📋 Table of Contents

- [Overview](#-overview)
- [Key Features](#-key-features)
- [Architecture](#-architecture)
- [Project Structure](#-project-structure)
- [Prerequisites](#-prerequisites)
- [Setup & Installation](#-setup--installation)
- [Running the Application](#-running-the-application)
- [API Documentation](#-api-documentation)
- [Demo Scenarios](#-demo-scenarios)
- [How It Works](#-how-it-works)
- [The Control Point](#-the-control-point)
- [Technical Highlights](#-technical-highlights)
- [Troubleshooting](#-troubleshooting)

---

## 🎯 Overview

This project demonstrates **how Java engineers control AI behavior** in a real-world application. Built for a college technical talk, it showcases:

- ✅ **AI Integration**: Using Google Gemini API in a Spring Boot backend
- ✅ **Engineering Control**: Java enforces strict rules on what AI can discuss
- ✅ **Conversational UX**: Chatbot responds naturally but stays on-topic
- ✅ **Stateless Design**: No database, no sessions - pure request/response
- ✅ **Production Patterns**: Proper separation of concerns, validation, error handling

### Core Philosophy

> **"AI speaks like a human, but engineers decide what it's allowed to talk about."**

This project proves that AI is a **controlled tool**, not a black box.

---

## ✨ Key Features

| Feature | Description |
|---------|-------------|
| 🤖 **Conversational AI** | Responds like a friendly college assistant using Gemini 1.5 Flash |
| 🛡️ **Scope Enforcement** | Only answers college-related questions; refuses off-topic queries gracefully |
| 🎨 **Modern Frontend** | Beautiful, responsive chat UI with animations and dark mode |
| ⚡ **Fast & Stateless** | No memory, no database - every request is independent |
| 🔒 **Input Validation** | Guardrails prevent empty inputs, excessive length, and API abuse |
| 🚨 **Fallback Handling** | Graceful error messages when API fails |
| 📝 **Clean Code** | Beginner-friendly with extensive comments |

---

## 🏗️ Architecture

```
┌─────────────────┐
│  User (Browser) │
│   or Postman    │
└────────┬────────┘
         │
         ▼
┌─────────────────────────────┐
│   Spring Boot REST API      │
│   ChatController.java       │
└────────┬────────────────────┘
         │
         ▼
┌─────────────────────────────┐
│   ChatService.java          │
│   • Input Validation        │
│   • Business Logic          │
│   • Error Handling          │
└────────┬────────────────────┘
         │
         ▼
┌─────────────────────────────┐
│   GeminiClient.java         │
│   • API Communication       │
│   • Request Builder         │
│   • Response Parser         │
└────────┬────────────────────┘
         │
         ▼
┌─────────────────────────────┐
│   PromptConfig.java         │← 🎯 THE CONTROL CENTER
│   System Prompt with:       │
│   • Personality rules       │
│   • Scope boundaries        │
│   • Response style          │
└────────┬────────────────────┘
         │
         ▼
┌─────────────────────────────┐
│   Google Gemini API         │
│   (AI Model)                │
└────────┬────────────────────┘
         │
         ▼
    Conversational
      Response
```

---

## 📁 Project Structure

```
college-chatbot/
├── pom.xml                                      # Maven dependencies
├── README.md                                    # This file
└── src/main/
    ├── java/com/college/chatbot/
    │   ├── CollegeChatbotApplication.java       # Main Spring Boot app
    │   ├── controller/
    │   │   └── ChatController.java              # REST API endpoint
    │   ├── service/
    │   │   └── ChatService.java                 # Business logic & validation
    │   ├── client/
    │   │   └── GeminiClient.java                # Gemini API integration
    │   ├── config/
    │   │   └── PromptConfig.java                # 🎯 AI CONTROL CENTER
    │   └── dto/
    │       ├── ChatRequest.java                 # Request DTO
    │       └── ChatResponse.java                # Response DTO
    └── resources/
        ├── application.yml                      # Configuration
        └── static/                              # Frontend
            ├── index.html                       # Chat UI
            ├── css/styles.css                   # Modern styling
            └── js/app.js                        # Frontend logic
```

---

## 📋 Prerequisites

Before running this project, ensure you have:

1. **Java 17** or higher
   - Check: `java -version`
   - Download: [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://adoptium.net/)

2. **Maven 3.6+**
   - Check: `mvn -version`
   - Download: [Apache Maven](https://maven.apache.org/download.cgi)

3. **Google Gemini API Key** (free tier)
   - Get yours: [Google AI Studio](https://makersuite.google.com/app/apikey)
   - No payment required for free tier

---

## ⚙️ Setup & Installation

### Step 1: Clone/Download the Project

```bash
cd college-chatbot
```

### Step 2: Configure API Key

You have two options:

**Option A: Environment Variable (Recommended)**
```bash
# Windows (PowerShell)
$env:GEMINI_API_KEY="your-api-key-here"

# Windows (CMD)
set GEMINI_API_KEY=your-api-key-here

# Linux/Mac
export GEMINI_API_KEY=your-api-key-here
```

**Option B: Direct Configuration**

Edit `src/main/resources/application.yml`:
```yaml
gemini:
  api:
    key: your-actual-api-key-here
```

### Step 3: Build the Project

```bash
mvn clean install
```

This will:
- Download all dependencies
- Compile Java code
- Run tests (if any)

---

## 🚀 Running the Application

### Start the Server

```bash
mvn spring-boot:run
```

You should see:
```
╔═══════════════════════════════════════════════════════════╗
║       🎓 COLLEGE AI CHATBOT - Ready to Serve! 🤖         ║
╠═══════════════════════════════════════════════════════════╣
║  API Endpoint: POST http://localhost:8080/api/chat        ║
║  Frontend: http://localhost:8080                          ║
╚═══════════════════════════════════════════════════════════╝
```

### Access the Application

- **Frontend UI**: Open browser → [http://localhost:8080](http://localhost:8080)
- **API Endpoint**: `http://localhost:8080/api/chat`
- **Health Check**: `http://localhost:8080/api/health`

---

## 📡 API Documentation

### POST `/api/chat`

Send a question and get an AI response.

**Request:**
```json
{
  "question": "What courses are offered?"
}
```

**Response:**
```json
{
  "answer": "Sure 🙂 ABC College offers programs like B.E, B.Tech, MCA, and M.Tech. Would you like details about any specific course?"
}
```

**Status Codes:**
- `200 OK` - Success
- `400 Bad Request` - Invalid input (empty, too long)
- `500 Internal Server Error` - API failure

### Testing with curl

```bash
# Valid college question
curl -X POST http://localhost:8080/api/chat \
  -H "Content-Type: application/json" \
  -d '{"question": "What courses are offered?"}'

# Out-of-scope question (should refuse)
curl -X POST http://localhost:8080/api/chat \
  -H "Content-Type: application/json" \
  -d '{"question": "Who will win IPL?"}'

# Empty question (should return error)
curl -X POST http://localhost:8080/api/chat \
  -H "Content-Type: application/json" \
  -d '{"question": ""}'
```

### Testing with Postman

1. **Method**: `POST`
2. **URL**: `http://localhost:8080/api/chat`
3. **Headers**: `Content-Type: application/json`
4. **Body** (raw JSON):
   ```json
   {
     "question": "How are the placements?"
   }
   ```

---

## 🎬 Demo Scenarios

Perfect for your technical talk! Test these scenarios:

### ✅ Scenario 1: Valid College Question
**Question:** "What courses are offered?"  
**Expected:** Friendly, conversational answer about courses

### ✅ Scenario 2: Placement Query
**Question:** "How are the placements?"  
**Expected:** Information about placements in chat style

### ✅ Scenario 3: Admissions Question
**Question:** "What is the admission process?"  
**Expected:** Clear explanation with helpful tone

### ❌ Scenario 4: Out-of-Scope Question
**Question:** "Who will win IPL?"  
**Expected:** 
```
I can only help with questions related to ABC College 🙂
```

### ❌ Scenario 5: Empty Input
**Question:** `""`  
**Expected:** Validation error message

### ❌ Scenario 6: API Failure Simulation
Turn off internet or use invalid API key  
**Expected:** 
```
Sorry 😕 the system is temporarily unavailable. Please try again later.
```

---

## 🧠 How It Works

### Why is this Stateless?

**No Database** → No conversation history  
**No Sessions** → Each request is independent  
**No Memory** → AI doesn't "remember" previous questions

**Why?**
- Simplicity for demo purposes
- Focuses on AI control, not storage
- Easier to understand and explain
- Production-ready pattern for simple use cases

### How Does Java Control AI?

The magic happens in **[PromptConfig.java](src/main/java/com/college/chatbot/config/PromptConfig.java)**:

```java
public String getSystemPrompt() {
    return """
        You are a friendly AI assistant for ABC College.
        
        ✅ YOU CAN ANSWER questions about:
        - Courses, Admissions, Placements, etc.
        
        ❌ YOU CANNOT ANSWER questions about:
        - Sports, Politics, Entertainment, etc.
        
        Out-of-scope → respond: "I can only help with 
        questions related to ABC College 🙂"
        """;
}
```

This prompt is **prepended to every user question** before sending to Gemini. The AI follows these instructions, making it seem "controlled."

---

## 🎯 The Control Point

### Open `PromptConfig.java` During Your Talk

**SAY THIS:**

> *"This single file is where we control the entire AI's behavior. No matter how powerful the model is, it will follow these rules because we engineered it to. This is the difference between using AI and controlling AI."*

**SHOW:**
- Where personality is defined
- Where scope boundaries are set
- Where refusal messages are enforced

**EXPLAIN:**
- Engineers write this prompt
- AI executes the prompt
- Result: Controlled, predictable behavior

---

## 💡 Technical Highlights

### 1. **Separation of Concerns**
- `Controller` → Handles HTTP
- `Service` → Business logic
- `Client` → External API calls
- `Config` → AI rules

### 2. **Input Validation**
- `@NotBlank` annotation on DTO
- Manual validation in service layer
- 300-character limit enforced

### 3. **Error Handling**
- Try-catch in service
- Fallback messages
- Never expose internal errors to users

### 4. **RestTemplate**
- Built-in Spring HTTP client
- Clean JSON handling
- Easy to mock for testing

### 5. **Lombok**
- Reduces boilerplate (`@Data`, `@AllArgsConstructor`)
- Cleaner DTOs

### 6. **CORS Enabled**
- Allows frontend to call API from same domain
- Production-ready with `@CrossOrigin`

---

## 🐛 Troubleshooting

### Issue: "API Key Not Found"
**Solution:** Set `GEMINI_API_KEY` environment variable or update `application.yml`

### Issue: Port 8080 Already in Use
**Solution:** Change port in `application.yml`:
```yaml
server:
  port: 9090
```

### Issue: "Connection Refused"
**Solution:** Check internet connection; Gemini API requires network access

### Issue: AI Not Refusing Out-of-Scope Questions
**Solution:** Review `PromptConfig.java` - ensure prompt is clear and specific

### Issue: Build Fails
**Solution:** 
```bash
mvn clean install -U
```

---

## 📚 Learning Outcomes

After exploring this project, you understand:

✅ How to integrate external AI APIs in Spring Boot  
✅ How to enforce rules on AI behavior via prompts  
✅ How to build stateless REST APIs  
✅ How to validate inputs before reaching AI  
✅ How to handle errors gracefully  
✅ How to structure production-ready Java code

---

## 🎤 Presentation Tips

### During Your Talk:

1. **Show the Frontend** → Let students see the chat UI
2. **Ask a College Question** → Demo natural conversation
3. **Ask "Who will win IPL?"** → Show refusal (IMPACT MOMENT)
4. **Open PromptConfig.java** → Say: *"This is how engineers control AI"*
5. **Explain Stateless Design** → Show no database, just code
6. **Q&A** → Let students ask questions

### Key Message

> **Engineers don't just use AI—they architect it, control it, and make it safe.**

---

## 🤝 Contributing

This is a demo project for educational purposes. Feel free to:
- Fork and modify for your use case
- Add features (database, authentication, etc.)
- Use as a learning resource

---

## 📄 License

MIT License - Free to use for educational purposes

---

## 👨‍💻 Author

**Demo Project for College Technical Talk**  
*Showcasing AI Integration in Java Backend*

---

## 🙏 Acknowledgments

- **Google Gemini API** - For free-tier AI access
- **Spring Boot** - For rapid backend development
- **Computer Science Students** - The future builders 🚀

---

**Happy Coding! 🎓💻**
