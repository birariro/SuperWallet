package com.example.superwallet.domain.model

data class CardData (
    val cardTitle:String,
    val cardCode :String,
    val cardType :CardType
)
enum class CardType{
    BARCODE, QR
}