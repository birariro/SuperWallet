package com.example.superwallet.presenter.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superwallet.domain.usecase.LoginUseCase
import com.example.superwallet.domain.model.LoginFormStateData
import com.example.superwallet.domain.model.LoginResultData
import com.example.superwallet.domain.usecase.ValidDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase, private val validDataUseCase: ValidDataUseCase) :ViewModel() {
    private val _loginFormState = MutableLiveData<LoginFormStateData>()
    val loginFormState: LiveData<LoginFormStateData> = _loginFormState

    private val _loginResult = MutableLiveData<LoginResultData>()
    val loginResult :LiveData<LoginResultData> = _loginResult

    fun validLoginData(id:String,pw:String){
        _loginFormState.value = validDataUseCase.validIDAndPWD(id,pw)
    }

    fun login(id:String, pw:String){
        print("로그인 클릭")
        if(_loginFormState.value?.validID == false || _loginFormState.value?.validPW == false){
            _loginResult.value = LoginResultData(success = false,errorCode = -1)
            return
        }

        viewModelScope.launch {
            _loginResult.value = loginUseCase.login(id,pw)
        }

    }

}