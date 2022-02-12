package com.example.superwallet.domain.repository

import com.example.superwallet.domain.model.CardData
import com.example.superwallet.domain.model.LoginData

interface CardRepository {
    suspend fun insertCard(cardData: CardData)
    suspend fun findAllCard() : List<CardData>
    suspend fun updateCard(cardData: CardData)
}