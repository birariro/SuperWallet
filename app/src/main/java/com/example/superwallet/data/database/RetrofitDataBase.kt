package com.example.superwallet.data.database

import retrofit2.http.GET

interface RetrofitDataBase {
    @GET("v3/e81f0688-972a-40fb-b42b-f89d90b202ca")
    suspend fun getRepos() : String

}

