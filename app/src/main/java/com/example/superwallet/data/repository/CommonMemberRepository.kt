package com.example.superwallet.data.repository

import com.example.superwallet.data.datasource.LocalDataSource
import com.example.superwallet.data.datasource.RemoteDataSource
import com.example.superwallet.data.entity.LoginEntity
import com.example.superwallet.domain.model.LoginData
import com.example.superwallet.domain.repository.MemberRepository


class CommonMemberRepository (private val remoteDataSource: RemoteDataSource,val localDataSource: LocalDataSource):MemberRepository {

    override suspend fun login(loginData: LoginData): String {
        return remoteDataSource.login(loginData.id,loginData.pw)
    }

    override suspend fun findLoginData(): LoginData {
        var result = localDataSource.findLoginData()
        return  LoginData(id = result?.id ?: "" , pw= result?.password ?: "")

    }

    override suspend fun saveLoginData(loginData: LoginData) {
        localDataSource.saveLoginData(LoginEntity(id = loginData.id, password = loginData.pw))
    }

    override suspend fun deleteLoginData() {
        localDataSource.deleteLoginData()
    }

    override fun findID() {
        TODO("Not yet implemented")
    }

    override fun findPW() {
        TODO("Not yet implemented")
    }
}