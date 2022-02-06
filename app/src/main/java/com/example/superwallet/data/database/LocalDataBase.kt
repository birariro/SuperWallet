package com.example.superwallet.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.superwallet.data.database.dao.LoginDAO
import com.example.superwallet.data.entity.LoginEntity

@Database(entities = [LoginEntity::class], version = 1 )
abstract class LocalDataBase :RoomDatabase(){
    abstract fun loginDAO(): LoginDAO
}