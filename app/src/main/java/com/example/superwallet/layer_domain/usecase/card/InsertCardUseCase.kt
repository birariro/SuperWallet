package com.example.superwallet.layer_domain.usecase.card

import com.example.superwallet.layer_domain.model.CardData
import com.example.superwallet.layer_domain.repository.CardRepository

class InsertCardUseCase(private val cardRepository: CardRepository) {
    suspend fun execute(cardData: CardData){
        cardRepository.insertCard(cardData)
    }
}