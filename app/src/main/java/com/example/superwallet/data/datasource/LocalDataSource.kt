package com.example.superwallet.data.datasource

import com.example.superwallet.data.database.LocalDataBase
import com.example.superwallet.data.entity.CardEntity
import com.example.superwallet.data.entity.LoginEntity

class LocalDataSource(private val localDataBase: LocalDataBase) {
    suspend fun findLoginData() : LoginEntity {
        return localDataBase.loginDAO().get()
    }
    suspend fun saveLoginData(loginEntity: LoginEntity){
        //기존의 모든 정보 제거후 추가
        var loginDataList = localDataBase.loginDAO().getAll()
        for (loginEntity in loginDataList) {
            localDataBase.loginDAO().delete(loginEntity)
        }
        localDataBase.loginDAO().insert(loginEntity)
    }

    suspend fun insertCardData(cardEntity: CardEntity){
        print("[k4keye] LocalDataSource")
        localDataBase.cardDAO().insert(cardEntity)
    }
    suspend fun findAllCardData():List<CardEntity>{
        return localDataBase.cardDAO().getAll()
    }
}