package com.example.data

import kotlinx.coroutines.flow.Flow

class ChatRepository(private val chatDao: ChatDao) {
    val recentSessions: Flow<List<ChatSession>> = chatDao.getRecentSessions()

    fun searchSessions(query: String): Flow<List<ChatSession>> = chatDao.searchSessions(query)

    suspend fun insertSession(session: ChatSession) = chatDao.insertSession(session)
    suspend fun deleteSession(sessionId: String) = chatDao.deleteSession(sessionId)

    fun getMessagesForSession(sessionId: String): Flow<List<ChatMessage>> = chatDao.getMessagesForSession(sessionId)
    suspend fun insertMessage(message: ChatMessage) = chatDao.insertMessage(message)
}
