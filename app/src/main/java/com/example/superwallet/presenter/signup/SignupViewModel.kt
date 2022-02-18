package com.example.superwallet.presenter.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superwallet.domain.model.LoginData
import com.example.superwallet.domain.usecase.member.SignupUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(private val signupUseCase: SignupUseCase):ViewModel() {


    fun signup(id:String,pw:String){
        val loginData = LoginData(id, pw)
        viewModelScope.launch {
            signupUseCase.execute(loginData)
        }

    }
}