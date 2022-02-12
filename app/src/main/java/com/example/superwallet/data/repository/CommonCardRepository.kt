package com.example.superwallet.data.repository

import android.util.Log
import com.example.superwallet.data.datasource.LocalDataSource
import com.example.superwallet.data.entity.CardEntity
import com.example.superwallet.domain.model.CardData
import com.example.superwallet.domain.repository.CardRepository

class CommonCardRepository(private val localDataSource: LocalDataSource):CardRepository {
    override suspend fun insertCard(cardData: CardData) {
        var cardEntity = CardEntity(title = cardData.cardTitle, code= cardData.cardCode, type = cardData.cardType)
        localDataSource.insertCardData(cardEntity)
        findAllCard()
    }

    override suspend fun findAllCard(): List<CardData> {
        val findAllCardData = localDataSource.findAllCardData()
        for (findAllCardDatum in findAllCardData) {
            Log.d("CommonCardRepository", "[k4keye] find card : ${findAllCardDatum}")
        }
        return mutableListOf()
    }

    override suspend fun updateCard(cardData: CardData) {
        TODO("Not yet implemented")
    }
}