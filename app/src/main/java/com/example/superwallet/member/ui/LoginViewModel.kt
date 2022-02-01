package com.example.superwallet.member.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.superwallet.member.domain.usecase.LoginUseCase
import com.example.superwallet.member.ui.data.LoginFormStateData
import com.example.superwallet.member.ui.data.LoginResultData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) :ViewModel() {
    private val _loginFormState = MutableLiveData<LoginFormStateData>()
    val loginFormState: LiveData<LoginFormStateData> = _loginFormState

    private val _loginResult = MutableLiveData<LoginResultData>()
    val loginResult :LiveData<LoginResultData> = _loginResult

    fun validLoginData(id:String,pw:String){
        if(!validID(id)){
            _loginFormState.value = LoginFormStateData(validID = false, validPW = false)
        }else if(!validPW(pw)){
            _loginFormState.value = LoginFormStateData(validID = true, validPW = false)
        }else{
            _loginFormState.value = LoginFormStateData(validID = true, validPW = true)
        }

    }

    fun login(id:String, pw:String){
        if(!validID(id) || !validPW(pw)){
            _loginResult.value = LoginResultData(success = false,errorCode = -1)
        }else{

            _loginResult.value = LoginResultData(success = true,errorCode = 0)
        }
        loginUseCase.login(id,pw)
    }

    private fun validID(id:String):Boolean{
        return id.length >= 5
    }
    private fun validPW(pw:String):Boolean{
        return pw.length >= 8
    }
}