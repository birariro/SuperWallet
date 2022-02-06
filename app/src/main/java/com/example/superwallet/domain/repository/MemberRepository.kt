package com.example.superwallet.domain.repository

import com.example.superwallet.domain.model.LoginData

interface MemberRepository {
    suspend fun login(loginData: LoginData) :String
    suspend fun reLogin() : LoginData
    fun findID()
    fun findPW()

}