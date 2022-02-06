package com.example.superwallet.data.database.dao

import androidx.room.*
import com.example.superwallet.data.entity.LoginEntity

@Dao
interface LoginDAO {
    @Query("SELECT * FROM LoginEntity")
    suspend fun getAll(): List<LoginEntity>

    @Query("SELECT * FROM LoginEntity limit 1")
    suspend fun get(): LoginEntity

    @Insert
    suspend fun insert(todo: LoginEntity)

    @Update
    suspend fun update(todo: LoginEntity)

    @Delete
    suspend fun delete(todo: LoginEntity)

}