package com.example.superwallet.data.datasource

import com.example.superwallet.domain.usecase.member.LoginStateUseCase
import com.example.superwallet.domain.usecase.member.SignupStateUseCase
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
    suspend fun signup(id:String,pw:String){
        val mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser

        mAuth.createUserWithEmailAndPassword(id, pw)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = mAuth.currentUser
                    SignupStateUseCase.setSignupResult(true)
                } else {
                    SignupStateUseCase.setSignupResult(false)
                }
            }

    }


}