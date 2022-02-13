package com.example.superwallet.domain.usecase

import android.util.Log
import com.example.superwallet.domain.model.LoginData
import com.example.superwallet.domain.model.LoginResultData
import com.example.superwallet.domain.repository.MemberRepository
import com.example.superwallet.presenter.login.LoginViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginUseCase (private val memberRepository: MemberRepository){
    @Deprecated(message = "파이어베이스 로그인으로 변경되어 API 로그인 중지")
    suspend fun login(loginData: LoginData):LoginResultData{
        var result =  memberRepository.login(loginData)

        if(result.length > 5){
            return LoginResultData(success = true,errorCode = 0)
        }
        return LoginResultData(success = false,errorCode = -1)
    }


    fun firebaseLogin(loginData: LoginData , completion:(LoginResultData)-> Unit){
        val mAuth = FirebaseAuth.getInstance()
        var result :Boolean
        mAuth.signInWithEmailAndPassword(loginData.id, loginData.pw)
            .addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    completion(LoginResultData(success = true,errorCode = 0))
                    true
                } else {
                    completion(LoginResultData(success = false,errorCode = -1))
                }

            }

    }

}