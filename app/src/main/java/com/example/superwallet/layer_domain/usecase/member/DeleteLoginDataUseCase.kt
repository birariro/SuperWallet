package com.example.superwallet.layer_domain.usecase.member

import com.example.superwallet.layer_domain.repository.MemberRepository

class DeleteLoginDataUseCase(private val memberRepository: MemberRepository) {
    suspend fun execute() {
        memberRepository.deleteLoginData()
    }
}