package com.example.superwallet.domain.repository

import com.example.superwallet.domain.model.LoginData

interface MemberRepository {
    suspend fun login(loginData: LoginData) :String
    suspend fun findLoginData() : LoginData
    suspend fun saveLoginData(loginData: LoginData)
    suspend fun deleteLoginData()
    fun findID()
    fun findPW()

}