package com.example.superwallet.data.repository

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
        val findAllCardDataList = localDataSource.findAllCardData()
        val cardDataList = mutableListOf<CardData>()
        for (findAllCardData in findAllCardDataList) {
            cardDataList.add(CardData(id =findAllCardData.id ,cardTitle = findAllCardData.title, cardCode = findAllCardData.code, cardType = findAllCardData.type))
        }
        return cardDataList
    }

    override suspend fun updateCard(cardData: CardData) {
        val cardEntity = cardDataToCardEntity(cardData)
        localDataSource.updateCardData(cardEntity)
    }

    override suspend fun deleteCard(cardData: CardData) {
        val cardEntity = cardDataToCardEntity(cardData)
        localDataSource.deleteCardData(cardEntity)
    }

    private fun cardDataToCardEntity(cardData: CardData): CardEntity {
        return CardEntity(
            id = cardData.id,
            title = cardData.cardTitle,
            code = cardData.cardCode,
            type = cardData.cardType
        )
    }
}