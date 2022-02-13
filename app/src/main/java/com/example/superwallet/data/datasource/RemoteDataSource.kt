package com.example.superwallet.data.datasource

import android.util.Log
import com.example.superwallet.data.database.RetrofitDataBase
import com.example.superwallet.presenter.login.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Retrofit

class RemoteDataSource(private val retrofit: Retrofit) {
    suspend fun login(id:String, pw:String) : String{
        return retrofit.create(RetrofitDataBase::class.java).getRepos()
    }

}
