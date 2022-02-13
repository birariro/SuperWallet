package com.example.superwallet.presenter.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superwallet.domain.usecase.DeleteCardUseCase
import com.example.superwallet.domain.usecase.DeleteLoginDataUseCase
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(private val deleteLoginDataUseCase: DeleteLoginDataUseCase): ViewModel() {

    fun logout(){
        val mAuth = FirebaseAuth.getInstance()
        mAuth.signOut()
        viewModelScope.launch {
            deleteLoginDataUseCase.execute()
        }
    }
}