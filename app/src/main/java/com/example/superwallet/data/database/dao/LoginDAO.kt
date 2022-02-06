package com.example.superwallet.data.database.dao

import androidx.room.*
import com.example.superwallet.data.entity.LoginEntity

@Dao
interface LoginDAO {
    @Query("SELECT * FROM LoginEntity")
    fun getAll(): List<LoginEntity>

    @Insert
    fun insert(todo: LoginEntity)

    @Update
    fun update(todo: LoginEntity)

    @Delete
    fun delete(todo: LoginEntity)

}