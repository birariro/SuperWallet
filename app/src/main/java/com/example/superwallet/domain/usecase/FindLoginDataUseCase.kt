package com.example.superwallet.domain.usecase

import com.example.superwallet.domain.model.LoginData
import com.example.superwallet.domain.repository.MemberRepository

class FindLoginDataUseCase (private val memberRepository: MemberRepository) {
    suspend fun execute():LoginData{
        return memberRepository.findLoginData()
    }

}