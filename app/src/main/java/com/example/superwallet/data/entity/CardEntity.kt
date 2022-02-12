package com.example.superwallet.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.superwallet.domain.model.CardType

@Entity
data class CardEntity(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    var title : String,
    var code :String,
    var type : CardType
)

