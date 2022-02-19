package com.example.superwallet.data.datasource

import android.util.Log
import com.example.superwallet.data.database.RetrofitDataBase
import retrofit2.Retrofit

class RemoteDataSource(private val retrofit: Retrofit) {
    suspend fun login(id:String, pw:String) {
        val result =  retrofit.create(RetrofitDataBase::class.java).getRepos()
        Log.d("로그인","로그인 $result")

    }



}
