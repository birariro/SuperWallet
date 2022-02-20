package com.example.superwallet.layer_data.datasource

import android.util.Log
import com.example.superwallet.layer_domain.model.CommonResultData
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class FireBaseDataSource {
    companion object{
        val mAuth = FirebaseAuth.getInstance()
    }

    suspend fun login(id:String, pw:String) : Flow<CommonResultData> = callbackFlow{
        Log.d("FireBaseDataSource" , "false login Flow 진입")

        mAuth.signOut()
        mAuth.signInWithEmailAndPassword(id, pw)

        mAuth.addAuthStateListener{
            if(it == null){
                trySendBlocking(CommonResultData(false,-1)).onFailure{
                    Log.d("FireBaseDataSource" , "false login Flow : ${it}")
                }
            }else{
                trySendBlocking(CommonResultData(true, 0)).onFailure{
                    Log.d("FireBaseDataSource" , "true login Flow : ${it}")
                }
            }
        }
        awaitClose {

        }

    }
    suspend fun signup(id:String,pw:String): Flow<CommonResultData> = callbackFlow {
        mAuth.createUserWithEmailAndPassword(id, pw)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = mAuth.currentUser
                    trySendBlocking(CommonResultData(true,0)).onFailure{
                        Log.d("FireBaseDataSource" , "true signup Flow : ${it}")
                    }
                } else {
                    trySendBlocking(CommonResultData(false,-1)).onFailure{
                        Log.d("FireBaseDataSource" , "false signup Flow : ${it}")
                    }
                }
            }

        awaitClose {

        }

    }


}