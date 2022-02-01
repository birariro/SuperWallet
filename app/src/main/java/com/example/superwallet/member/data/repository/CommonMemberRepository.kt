package com.example.superwallet.member.data.repository

import com.example.superwallet.member.data.datasource.RemoteDataSource
import com.example.superwallet.member.domain.repository.MemberRepository


class CommonMemberRepository (private val remoteDataSource: RemoteDataSource):MemberRepository {

    override suspend fun login(id: String, pw: String): String {
        return remoteDataSource.login(id,pw)
    }

    override fun findID() {
        TODO("Not yet implemented")
    }

    override fun findPW() {
        TODO("Not yet implemented")
    }
}