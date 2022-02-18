package com.example.superwallet.domain.usecase.member

import com.example.superwallet.domain.model.LoginData
import com.example.superwallet.domain.repository.MemberRepository

class SignupUseCase (private val memberRepository: MemberRepository){
    suspend fun execute(loginData: LoginData) {
        memberRepository.signup(loginData)
    }
}