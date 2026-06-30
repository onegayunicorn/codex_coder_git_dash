package com.example

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ChatViewModel(private val repository: ChatRepository) : ViewModel() {
    val recentSessions = repository.recentSessions.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    
    private val _selectedModel = MutableStateFlow("Gemini Pro")
    val selectedModel = _selectedModel.asStateFlow()

    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages = _messages.asStateFlow()

    fun selectModel(model: String) {
        _selectedModel.value = model
    }

    fun sendMessage(text: String, sessionId: String) {
        viewModelScope.launch {
            repository.insertMessage(ChatMessage(sessionId = sessionId, role = "user", text = text))
            val prompt = "$text (Using model: ${_selectedModel.value})"
            // Use Gemini REST API as per skill guidelines
            val responseText = try {
                // Simplified call to Gemini API for demonstration
                // In a real scenario, this would involve Retrofit/GeminiService
                "Mock response from ${_selectedModel.value} to: $text"
            } catch (e: Exception) {
                "Error communicating with AI: ${e.message}"
            }
            repository.insertMessage(ChatMessage(sessionId = sessionId, role = "assistant", text = responseText))
        }
    }
}

class ChatViewModelFactory(private val repository: ChatRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChatViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ChatViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
