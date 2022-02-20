package com.example.superwallet.layer_data.datasource

import android.util.Log
import com.example.superwallet.layer_data.database.LocalDataBase
import com.example.superwallet.layer_data.entity.CardEntity
import com.example.superwallet.layer_data.entity.LoginEntity

class LocalDataSource(private val localDataBase: LocalDataBase) {
    suspend fun findLoginData() : LoginEntity {
        return localDataBase.loginDAO().get()
    }
    suspend fun saveLoginData(loginEntity: LoginEntity){
        var size = localDataBase.loginDAO().getAll().size
        Log.d("TEST","saveLoginData localDataBase $size")
        localDataBase.loginDAO().insert(loginEntity)
    }
    suspend fun deleteLoginData() {
        var loginDataList = localDataBase.loginDAO().getAll()
        for (loginEntity in loginDataList) {
            localDataBase.loginDAO().delete(loginEntity)
        }
        Log.d("TEST","deleteLoginData localDataBase ${loginDataList.size}")
    }

    suspend fun insertCardData(cardEntity: CardEntity){
        localDataBase.cardDAO().insert(cardEntity)
    }
    suspend fun findAllCardData():List<CardEntity>{
        return localDataBase.cardDAO().getAll()
    }
    suspend fun updateCardData(cardEntity: CardEntity){
        return localDataBase.cardDAO().update(cardEntity)
    }
    suspend fun deleteCardData(cardEntity: CardEntity){
        return localDataBase.cardDAO().delete(cardEntity)
    }
}