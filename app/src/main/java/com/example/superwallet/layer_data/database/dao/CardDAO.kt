package com.example.superwallet.layer_data.database.dao

import androidx.room.*
import com.example.superwallet.layer_data.entity.CardEntity

@Dao
interface CardDAO {
    @Query("SELECT * FROM CardEntity")
    suspend fun getAll(): List<CardEntity>

    @Insert
    suspend fun insert(cardEntity: CardEntity)

    @Update
    suspend fun update(cardEntity: CardEntity)

    @Delete
    suspend fun delete(cardEntity: CardEntity)

}