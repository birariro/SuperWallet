package com.example.superwallet.domain.usecase

import androidx.lifecycle.viewModelScope
import com.example.superwallet.domain.model.CardData
import com.example.superwallet.domain.repository.MemberRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class LogoutUseCase(private val deleteLoginDataUseCase: DeleteLoginDataUseCase){
    suspend fun execute(){
        val mAuth = FirebaseAuth.getInstance()
        mAuth.signOut()
        deleteLoginDataUseCase.execute()
    }
}