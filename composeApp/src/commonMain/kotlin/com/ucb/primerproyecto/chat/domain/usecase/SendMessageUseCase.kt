package com.ucb.primerproyecto.chat.domain.usecase

import com.ucb.primerproyecto.chat.domain.model.MessageModel
import com.ucb.primerproyecto.chat.domain.repository.ChatRepository


class SendMessageUseCase(
    private val chatRepository: ChatRepository
) {
    suspend fun invoke(message: MessageModel, chatId: String) {

    }
}

// Individuales, Grupales