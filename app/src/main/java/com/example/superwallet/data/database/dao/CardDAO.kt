package com.example.superwallet.data.database.dao

import androidx.room.*
import com.example.superwallet.data.entity.CardEntity
import com.example.superwallet.data.entity.LoginEntity

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