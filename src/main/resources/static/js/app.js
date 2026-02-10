/**
 * 🚀 COLLEGE AI CHATBOT - Frontend JavaScript
 * 
 * PURPOSE:
 * Handles user interactions and API communication
 * 
 * FEATURES:
 * - Send messages to backend API
 * - Display responses in chat UI
 * - Handle loading states
 * - Quick action buttons
 * - Character counter
 * - Smooth animations
 */

// ============================================
// DOM ELEMENTS
// ============================================
const chatForm = document.getElementById('chatForm');
const userInput = document.getElementById('userInput');
const sendButton = document.getElementById('sendButton');
const chatMessages = document.getElementById('chatMessages');
const typingIndicator = document.getElementById('typingIndicator');
const charCount = document.getElementById('charCount');
const quickActions = document.getElementById('quickActions');
const quickActionButtons = document.querySelectorAll('.quick-action-btn');

// ============================================
// API CONFIGURATION
// ============================================
const API_URL = '/api/chat';

// ============================================
// CHARACTER COUNTER
// ============================================
userInput.addEventListener('input', () => {
    const count = userInput.value.length;
    charCount.textContent = `${count}/300`;

    // Change color if approaching limit
    if (count > 250) {
        charCount.style.color = '#f59e0b'; // Warning color
    } else {
        charCount.style.color = '#94a3b8'; // Default color
    }
});

// ============================================
// FORM SUBMISSION
// ============================================
chatForm.addEventListener('submit', async (e) => {
    e.preventDefault();

    const question = userInput.value.trim();

    if (!question) {
        return;
    }

    // Hide quick actions after first message
    quickActions.classList.add('hidden');

    // Add user message to chat
    addMessage(question, 'user');

    // Clear input
    userInput.value = '';
    charCount.textContent = '0/300';

    // Disable input while processing
    setLoadingState(true);

    // Show typing indicator
    showTypingIndicator();

    try {
        // Call backend API
        const response = await fetch(API_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ question })
        });

        if (!response.ok) {
            throw new Error('Failed to get response');
        }

        const data = await response.json();

        // Simulate slight delay for better UX (makes it feel more natural)
        setTimeout(() => {
            hideTypingIndicator();
            addMessage(data.answer, 'bot');
            setLoadingState(false);
        }, 500);

    } catch (error) {
        console.error('Error:', error);
        hideTypingIndicator();
        addMessage('Sorry 😕 the system is temporarily unavailable. Please try again later.', 'bot');
        setLoadingState(false);
    }
});

// ============================================
// QUICK ACTION BUTTONS
// ============================================
quickActionButtons.forEach(button => {
    button.addEventListener('click', () => {
        const question = button.getAttribute('data-question');
        userInput.value = question;
        chatForm.dispatchEvent(new Event('submit'));
    });
});

// ============================================
// MESSAGE DISPLAY FUNCTIONS
// ============================================

/**
 * Adds a message to the chat interface
 * @param {string} text - Message text
 * @param {string} type - 'user' or 'bot'
 */
function addMessage(text, type) {
    const messageDiv = document.createElement('div');
    messageDiv.className = `message ${type}-message`;

    const avatar = document.createElement('div');
    avatar.className = 'message-avatar';
    avatar.textContent = type === 'user' ? '👤' : '🤖';

    const content = document.createElement('div');
    content.className = 'message-content';

    const bubble = document.createElement('div');
    bubble.className = 'message-bubble';

    // Convert newlines to <br> and wrap in <p> tags
    const paragraphs = text.split('\n').filter(p => p.trim());
    bubble.innerHTML = paragraphs.map(p => `<p>${escapeHtml(p)}</p>`).join('');

    content.appendChild(bubble);
    messageDiv.appendChild(avatar);
    messageDiv.appendChild(content);

    chatMessages.appendChild(messageDiv);

    // Scroll to bottom smoothly
    scrollToBottom();
}

/**
 * Shows the typing indicator
 */
function showTypingIndicator() {
    typingIndicator.style.display = 'flex';
    scrollToBottom();
}

/**
 * Hides the typing indicator
 */
function hideTypingIndicator() {
    typingIndicator.style.display = 'none';
}

/**
 * Scrolls chat to bottom
 */
function scrollToBottom() {
    setTimeout(() => {
        chatMessages.scrollTop = chatMessages.scrollHeight;
    }, 100);
}

/**
 * Sets loading state (disables/enables input)
 * @param {boolean} isLoading
 */
function setLoadingState(isLoading) {
    userInput.disabled = isLoading;
    sendButton.disabled = isLoading;

    if (isLoading) {
        sendButton.style.opacity = '0.5';
    } else {
        sendButton.style.opacity = '1';
        userInput.focus();
    }
}

/**
 * Escapes HTML to prevent XSS
 * @param {string} text
 * @returns {string}
 */
function escapeHtml(text) {
    const div = document.createElement('div');
    div.textContent = text;
    return div.innerHTML;
}

// ============================================
// INITIALIZATION
// ============================================
document.addEventListener('DOMContentLoaded', () => {
    userInput.focus();
    console.log('🎓 College AI Chatbot initialized!');
});

// ============================================
// KEYBOARD SHORTCUTS
// ============================================
userInput.addEventListener('keydown', (e) => {
    // Ctrl/Cmd + Enter to send
    if ((e.ctrlKey || e.metaKey) && e.key === 'Enter') {
        chatForm.dispatchEvent(new Event('submit'));
    }
});
