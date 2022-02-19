package com.example.superwallet.domain.repository

import com.example.superwallet.domain.model.CommonResultData
import com.example.superwallet.domain.model.LoginData
import kotlinx.coroutines.flow.Flow

interface MemberRepository {
    suspend fun signup(loginData: LoginData) : Flow<CommonResultData>
    suspend fun login(loginData: LoginData) : Flow<CommonResultData>
    suspend fun findLoginData() : LoginData
    suspend fun saveLoginData(loginData: LoginData)
    suspend fun deleteLoginData()
    fun findID()
    fun findPW()

}