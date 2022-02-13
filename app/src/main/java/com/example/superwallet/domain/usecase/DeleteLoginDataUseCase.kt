package com.example.superwallet.domain.usecase

import com.example.superwallet.domain.model.LoginData
import com.example.superwallet.domain.repository.MemberRepository

class DeleteLoginDataUseCase(private val memberRepository: MemberRepository) {
    suspend fun execute() {
        memberRepository.deleteLoginData()
    }
}