package com.example.superwallet.domain.usecase

import com.example.superwallet.domain.model.LoginResultData
import com.example.superwallet.domain.repository.MemberRepository

class LoginUseCase (private val memberRepository: MemberRepository){
    suspend fun login(id:String, pw:String):LoginResultData{
        var result =  memberRepository.login(id,pw)
        if(result.length > 5){
            return LoginResultData(success = true,errorCode = 0)
        }
        return LoginResultData(success = false,errorCode = -1)
    }
}