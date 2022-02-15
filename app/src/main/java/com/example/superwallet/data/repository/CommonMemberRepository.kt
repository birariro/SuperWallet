package com.example.superwallet.data.repository

import android.util.Log
import com.example.superwallet.data.datasource.LocalDataSource
import com.example.superwallet.data.datasource.RemoteDataSource
import com.example.superwallet.data.entity.LoginEntity
import com.example.superwallet.domain.model.LoginData
import com.example.superwallet.domain.model.LoginResultData
import com.example.superwallet.domain.repository.MemberRepository
import com.example.superwallet.domain.usecase.LoginStateUseCase
import com.google.firebase.auth.FirebaseAuth


class CommonMemberRepository (private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource):MemberRepository {

//    override suspend fun login(loginData: LoginData) {
//        val result =  remoteDataSource.login(loginData.id,loginData.pw)
//        Log.d("로그인","로그인 $result")
//        if(result.length > 3){
//            LoginStateUseCase.setLoginResult(true)
//        }else{
//            LoginStateUseCase.setLoginResult(false)
//        }
//    }
    override suspend fun login(loginData: LoginData) {
        val mAuth = FirebaseAuth.getInstance()
        mAuth.signOut()
        mAuth.signInWithEmailAndPassword(loginData.id, loginData.pw)
        mAuth.addAuthStateListener {
            if(it == null){
                LoginStateUseCase.setLoginResult(false)
            }else{
                LoginStateUseCase.setLoginResult(true)
            }
        }

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