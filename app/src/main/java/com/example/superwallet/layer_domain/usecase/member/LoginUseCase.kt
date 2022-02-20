package com.example.superwallet.layer_domain.usecase.member

import com.example.superwallet.layer_domain.model.CommonResultData
import com.example.superwallet.layer_domain.model.LoginData
import com.example.superwallet.layer_domain.repository.MemberRepository
import kotlinx.coroutines.flow.Flow

class LoginUseCase (private val memberRepository: MemberRepository){

    suspend fun execute(loginData: LoginData) :  Flow<CommonResultData> {
        return memberRepository.login(loginData)
    }

}