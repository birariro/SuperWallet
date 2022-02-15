package com.example.superwallet.domain.usecase.card

import com.example.superwallet.domain.model.CardData
import com.example.superwallet.domain.repository.CardRepository

class InsertCardUseCase(private val cardRepository: CardRepository) {
    suspend fun execute(cardData: CardData){
        cardRepository.insertCard(cardData)
    }
}