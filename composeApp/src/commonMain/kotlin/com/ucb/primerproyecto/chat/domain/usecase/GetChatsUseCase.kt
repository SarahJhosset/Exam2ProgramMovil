package com.ucb.primerproyecto.chat.domain.usecase

import com.ucb.primerproyecto.chat.domain.model.ChatModel
import com.ucb.primerproyecto.chat.domain.repository.ChatRepository


class GetChatsUseCase(
    private val chatRepository: ChatRepository
) {
    suspend fun invoke(userId: String): List<ChatModel> {
        return chatRepository.getChats(userId)
    }
}