package com.example.superwallet.domain.usecase

import com.example.superwallet.domain.model.CardData
import com.example.superwallet.domain.repository.CardRepository

class FindAllCardUseCase(private val cardRepository: CardRepository) {
    suspend fun execute():List<CardData>{
       return cardRepository.findAllCard()
    }
}