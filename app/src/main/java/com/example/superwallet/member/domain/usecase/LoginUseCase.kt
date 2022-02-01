package com.example.superwallet.member.domain.usecase

import android.util.Log
import com.example.superwallet.member.domain.repository.MemberRepository
import javax.inject.Inject

class LoginUseCase constructor(private val memberRepository: MemberRepository){
    fun login(id:String,pw:String){
        Log.d("k4keye","usecase!!")
        memberRepository.login()
    }
}