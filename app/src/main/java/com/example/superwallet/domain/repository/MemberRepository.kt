package com.example.superwallet.domain.repository

interface MemberRepository {
    suspend fun login(id:String, pw:String) :String
    fun findID()
    fun findPW()

}