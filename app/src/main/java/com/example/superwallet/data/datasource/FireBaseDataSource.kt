package com.example.superwallet.data.datasource

import com.example.superwallet.domain.usecase.LoginStateUseCase
import com.google.firebase.auth.FirebaseAuth

class FireBaseDataSource {
    suspend fun login(id:String, pw:String) {
        val mAuth = FirebaseAuth.getInstance()
        mAuth.signOut()
        mAuth.signInWithEmailAndPassword(id, pw)
        mAuth.addAuthStateListener {
            if(it == null){
                LoginStateUseCase.setLoginResult(false)
            }else{
                LoginStateUseCase.setLoginResult(true)
            }
        }

    }
}