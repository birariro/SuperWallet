package com.example.superwallet.layer_domain.repository

import com.example.superwallet.layer_domain.model.CardData

interface CardRepository {
    suspend fun insertCard(cardData: CardData)
    suspend fun findAllCard() : List<CardData>
    suspend fun updateCard(cardData: CardData)
    suspend fun deleteCard(cardData: CardData)
}