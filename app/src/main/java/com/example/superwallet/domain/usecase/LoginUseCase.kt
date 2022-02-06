package com.example.superwallet.domain.usecase

import com.example.superwallet.domain.model.LoginData
import com.example.superwallet.domain.model.LoginResultData
import com.example.superwallet.domain.repository.MemberRepository

class LoginUseCase (private val memberRepository: MemberRepository){
    suspend fun login(loginData: LoginData):LoginResultData{
        var result =  memberRepository.login(loginData)
        if(result.length > 5){
            return LoginResultData(success = true,errorCode = 0)
        }
        return LoginResultData(success = false,errorCode = -1)
    }
}