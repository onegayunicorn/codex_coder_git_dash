package com.example

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.data.ChatMessage

@Composable
fun ChatScreen(viewModel: ChatViewModel) {
    val messages by viewModel.messages.collectAsState()
    var inputText by remember { mutableStateOf("") }
    val selectedModel by viewModel.selectedModel.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.weight(1f).padding(16.dp)) {
            items(messages) { message ->
                Text("${message.role}: ${message.text}")
            }
        }
        Row(modifier = Modifier.padding(8.dp)) {
            TextField(value = inputText, onValueChange = { inputText = it }, modifier = Modifier.weight(1f))
            Button(onClick = { viewModel.sendMessage(inputText, "session1"); inputText = "" }) {
                Text("Send")
            }
        }
    }
}
