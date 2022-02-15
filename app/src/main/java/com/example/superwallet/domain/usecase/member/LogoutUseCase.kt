package com.example.superwallet.domain.usecase.member

import com.google.firebase.auth.FirebaseAuth

class LogoutUseCase(private val deleteLoginDataUseCase: DeleteLoginDataUseCase){
    suspend fun execute(){
        deleteLoginDataUseCase.execute()
        val mAuth = FirebaseAuth.getInstance()
        mAuth.signOut()

    }
}