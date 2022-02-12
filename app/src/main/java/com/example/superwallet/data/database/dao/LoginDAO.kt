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
    suspend fun insert(loginEntity: LoginEntity)

    @Update
    suspend fun update(loginEntity: LoginEntity)

    @Delete
    suspend fun delete(loginEntity: LoginEntity)

}