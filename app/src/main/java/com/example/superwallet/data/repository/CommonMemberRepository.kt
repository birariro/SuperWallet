package com.example.superwallet.data.repository

import com.example.superwallet.data.datasource.LocalDataSource
import com.example.superwallet.data.datasource.RemoteDataSource
import com.example.superwallet.domain.model.LoginData
import com.example.superwallet.domain.repository.MemberRepository


class CommonMemberRepository (private val remoteDataSource: RemoteDataSource,val localDataSource: LocalDataSource):MemberRepository {

    override suspend fun login(loginData: LoginData): String {
        return remoteDataSource.login(loginData.id,loginData.pw)
    }

    override suspend fun reLogin(): LoginData {
        TODO("Not yet implemented")
    }

    override fun findID() {
        TODO("Not yet implemented")
    }

    override fun findPW() {
        TODO("Not yet implemented")
    }
}