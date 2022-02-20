package com.example.superwallet.layer_presenter.login

import android.util.Log
import androidx.lifecycle.*
import com.example.superwallet.layer_domain.model.LoginData
import com.example.superwallet.layer_domain.model.CommonResultData
import com.example.superwallet.layer_domain.usecase.*
import com.example.superwallet.layer_domain.usecase.member.FindLoginDataUseCase
import com.example.superwallet.layer_domain.usecase.member.InsertLoginDataUseCase
import com.example.superwallet.layer_domain.usecase.member.LoginUseCase
import com.example.superwallet.layer_presenter.login.model.AutoLoginData
import com.example.superwallet.layer_presenter.login.model.LoginFormStateData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val insertLoginDataUseCase: InsertLoginDataUseCase,
    private val findLoginDataUseCase: FindLoginDataUseCase,
    private val validDataUseCase: ValidDataUseCase
    ) :ViewModel() {
    companion object{
        const val TAG ="LoginViewModel"
    }
    //로그인 입력 폼 valid 데이터
    private val _loginFormState = MutableLiveData<LoginFormStateData>()
    val loginFormState: LiveData<LoginFormStateData> = _loginFormState

    //로그인 결과 데이터
    private val _loginResult = MutableLiveData<CommonResultData>()
    val loginResult :LiveData<CommonResultData> = _loginResult

    //자동 로그인 프로세스 데이터
    private val _autoLogin = MutableLiveData<AutoLoginData>()
    val autoLogin :LiveData<AutoLoginData> = _autoLogin


    init {
        //저장된 로그인 정보가있다면 auto login 을 진행한다.
        viewModelScope.launch {
            var result = findLoginDataUseCase.execute()
            if(result.id == "") return@launch
            _autoLogin.value = AutoLoginData(true,result.id,result.pw)
            login(result.id,result.pw)

            _autoLogin.value = AutoLoginData(false)
        }


    }
    fun validLoginData(id:String,pw:String){
        _loginFormState.value = validIDAndPWD(id,pw)
    }

    fun login(id:String, pw:String){
        val validLoginData = validIDAndPWD(id,pw)
        if(!validLoginData.validID || !validLoginData.validPW){
            _loginResult.value = CommonResultData(success = false,errorCode = -1)
            return
        }
        val loginData = LoginData(id =id, pw = pw)

        viewModelScope.launch {
            val loginResultFlow = loginUseCase.execute(loginData)
            loginResultFlow.collect {
                Log.d(TAG,"loginResultFlow : ${it.success}")
                if(_loginResult.value?.success != true){
                    _loginResult.value = it
                    if(it.success){
                        insertLoginDataUseCase.execute(loginData)
                    }
                }

            }

        }

    }

    private fun validIDAndPWD(id:String, pw:String) : LoginFormStateData {
        if(!validDataUseCase.execute(ValidDataUseCase.ValidDataType.id,id)){
            return LoginFormStateData(validID = false, validPW = false)
        }else if(!validDataUseCase.execute(ValidDataUseCase.ValidDataType.password,pw)){
            return LoginFormStateData(validID = true, validPW = false)
        }
        return LoginFormStateData(validID = true, validPW = true)

    }
}
