package com.example.superwallet.domain.usecase.member

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.superwallet.domain.model.LoginData
import com.example.superwallet.domain.model.LoginResultData
import com.example.superwallet.domain.repository.MemberRepository
import com.example.superwallet.presenter.login.LoginViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginUseCase (private val memberRepository: MemberRepository){

    suspend fun execute(loginData: LoginData){
        memberRepository.login(loginData)

    }

}