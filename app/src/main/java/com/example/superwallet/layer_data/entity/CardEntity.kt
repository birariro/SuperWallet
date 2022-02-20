package com.example.superwallet.layer_data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.superwallet.layer_domain.model.CardType

@Entity
data class CardEntity(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    var title : String,
    var code :String,
    var type : CardType
)

