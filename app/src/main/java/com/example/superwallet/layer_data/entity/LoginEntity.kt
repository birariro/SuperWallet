package com.example.superwallet.layer_data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LoginEntity(
    @PrimaryKey val id:String,
    var password : String
)
