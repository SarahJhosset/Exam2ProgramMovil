package com.ucb.primerproyecto.chat.domain.repository

import com.ucb.primerproyecto.chat.domain.model.ChatModel
import com.ucb.primerproyecto.chat.domain.model.MessageModel
import com.ucb.primerproyecto.profile.domain.model.ProfileModel

interface ChatRepository {
    suspend fun getChats(userId: String): List<ChatModel>
    suspend fun getChat(chatId: String): ChatModel
    suspend fun createChatIndividual(): ChatModel
    suspend fun createChatGroup(): ChatModel
    suspend fun getMessages(chatId: String): List<MessageModel>
}