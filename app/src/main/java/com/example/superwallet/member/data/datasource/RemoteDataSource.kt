package com.example.superwallet.member.data.datasource

import retrofit2.Retrofit

class RemoteDataSource(private val retrofit: Retrofit) {
    suspend fun login(id:String, pw:String) :String{
        return retrofit.create(RetrofitDataSource::class.java).getRepos()
    }

}
