package com.example.superwallet.member.domain.repository

interface MemberRepository {
    fun login()
    fun findID()
    fun findPW()

}