package com.example.superwallet.data.datasource

import com.example.superwallet.data.database.RetrofitDataBase
import retrofit2.Retrofit

class RemoteDataSource(private val retrofit: Retrofit) {
    suspend fun login(id:String, pw:String) :String{
        return retrofit.create(RetrofitDataBase::class.java).getRepos()
    }

}
