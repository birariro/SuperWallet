package com.example.superwallet.domain.usecase

import com.example.superwallet.domain.model.CardData
import com.example.superwallet.domain.model.LoginData
import com.example.superwallet.domain.repository.MemberRepository

class InsertLoginDataUseCase(private val memberRepository: MemberRepository) {
    suspend fun execute(loginData: LoginData){
        memberRepository.saveLoginData(loginData)
    }
}