package com.example.superwallet.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.superwallet.login.data.LoginFormStateData
import com.example.superwallet.login.data.LoginResultData

class LoginViewModel:ViewModel() {
    private val _loginFormState = MutableLiveData<LoginFormStateData>()
    val loginFormState: LiveData<LoginFormStateData> = _loginFormState

    private val _loginResult = MutableLiveData<LoginResultData>()
    val loginResult :LiveData<LoginResultData> = _loginResult

    fun validLoginData(id:String,pw:String){
        if(!validID(id)){
            _loginFormState.value = LoginFormStateData(validID = false, validPW = false)
        }else if(!validPW(pw)){
            _loginFormState.value = LoginFormStateData(validID = true, validPW = false)
        }
        _loginFormState.value = LoginFormStateData(validID = true, validPW = true)
    }

    private fun validID(id:String):Boolean{
        return id.length >= 5
    }
    private fun validPW(pw:String):Boolean{
        return pw.length >= 8
    }
}