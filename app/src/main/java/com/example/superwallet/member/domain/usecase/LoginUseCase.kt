package com.example.superwallet.member.domain.usecase

import android.util.Log
import com.example.superwallet.member.domain.repository.MemberRepository
import javax.inject.Inject

class LoginUseCase (private val memberRepository: MemberRepository){
    suspend fun login(id:String, pw:String):String{
        return memberRepository.login(id,pw)
    }
}