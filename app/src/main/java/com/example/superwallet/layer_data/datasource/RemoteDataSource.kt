package com.example.superwallet.layer_data.datasource

import android.util.Log
import com.example.superwallet.layer_data.database.RetrofitDataBase
import com.example.superwallet.layer_domain.model.CommonResultData
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Retrofit

class RemoteDataSource(private val retrofit: Retrofit) {

    suspend fun login(id:String, pw:String) : Flow<CommonResultData> = callbackFlow{
        val result =  retrofit.create(RetrofitDataBase::class.java).getRepos()
        Log.d("RemoteDataSource","Login Flow")
        if(result.length > 3){
            trySendBlocking(CommonResultData(true, 0)).onFailure{}
        }else{
                trySendBlocking(CommonResultData(false,-1)).onFailure{}
            }

        awaitClose {

        }

    }


}
