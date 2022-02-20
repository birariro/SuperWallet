package com.example.superwallet.layer_domain.model

import java.io.Serializable

data class CardData  (
    val id:Int = 0,
    val cardTitle:String,
    val cardCode :String,
    val cardType :CardType
) : Serializable
enum class CardType{
    BARCODE, QR
}