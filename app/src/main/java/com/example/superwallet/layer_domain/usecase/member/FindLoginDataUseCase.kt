package com.example.superwallet.layer_domain.usecase.member

import com.example.superwallet.layer_domain.model.LoginData
import com.example.superwallet.layer_domain.repository.MemberRepository

class FindLoginDataUseCase (private val memberRepository: MemberRepository) {
    suspend fun execute():LoginData{
        return memberRepository.findLoginData()
    }

}