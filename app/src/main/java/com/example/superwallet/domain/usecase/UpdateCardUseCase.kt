package com.example.superwallet.domain.usecase

import com.example.superwallet.domain.model.CardData
import com.example.superwallet.domain.repository.CardRepository

class UpdateCardUseCase(private val cardRepository: CardRepository) {
    suspend fun execute(cardData: CardData){
        cardRepository.updateCard(cardData)
    }
}