package com.example.superwallet.domain.usecase.member

import com.example.superwallet.domain.model.CommonResultData
import com.example.superwallet.domain.model.LoginData
import com.example.superwallet.domain.repository.MemberRepository
import kotlinx.coroutines.flow.Flow

class SignupUseCase (private val memberRepository: MemberRepository){
    suspend fun execute(loginData: LoginData) : Flow<CommonResultData> {
        return memberRepository.signup(loginData)
    }
}