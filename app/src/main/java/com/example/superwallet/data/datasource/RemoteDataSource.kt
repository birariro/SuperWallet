package com.example.superwallet.data.datasource

import android.util.Log
import com.example.superwallet.data.database.RetrofitDataBase
import com.example.superwallet.domain.model.LoginData
import com.example.superwallet.domain.usecase.LoginStateUseCase
import com.example.superwallet.presenter.login.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Retrofit

class RemoteDataSource(private val retrofit: Retrofit) {
    suspend fun login(id:String, pw:String) {
        val result =  retrofit.create(RetrofitDataBase::class.java).getRepos()
        Log.d("로그인","로그인 $result")
        if(result.length > 3){
            LoginStateUseCase.setLoginResult(true)
        }else{
            LoginStateUseCase.setLoginResult(false)
        }
    }



}
