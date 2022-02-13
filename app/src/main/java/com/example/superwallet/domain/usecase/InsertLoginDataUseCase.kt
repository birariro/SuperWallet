package com.example.superwallet.domain.usecase

import com.example.superwallet.domain.model.CardData
import com.example.superwallet.domain.model.LoginData
import com.example.superwallet.domain.repository.MemberRepository

class InsertLoginDataUseCase(private val memberRepository: MemberRepository ,private val deleteLoginDataUseCase: DeleteLoginDataUseCase) {
    suspend fun execute(loginData: LoginData){
        //모든 로그인 정보를 제거후 저장한다.
        deleteLoginDataUseCase.execute()
        memberRepository.saveLoginData(loginData)
    }
}