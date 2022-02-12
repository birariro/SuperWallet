package com.example.superwallet.presenter.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superwallet.domain.model.LoginData
import com.example.superwallet.domain.usecase.LoginUseCase
import com.example.superwallet.presenter.login.model.LoginFormStateData
import com.example.superwallet.domain.model.LoginResultData
import com.example.superwallet.presenter.login.model.AutoLoginData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) :ViewModel() {
    companion object{
        const val TAG ="LoginViewModel"
    }
    //로그인 입력 폼 valid 데이터
    private val _loginFormState = MutableLiveData<LoginFormStateData>()
    val loginFormState: LiveData<LoginFormStateData> = _loginFormState

    //로그인 결과 데이터
    private val _loginResult = MutableLiveData<LoginResultData>()
    val loginResult :LiveData<LoginResultData> = _loginResult

    //자동 로그인 프로세스 데이터
    private val _autoLogin = MutableLiveData<AutoLoginData>()
    val autoLogin :LiveData<AutoLoginData> = _autoLogin

    init {
        print("view model init 호출")
        viewModelScope.launch {

            var result = loginUseCase.reLogin()
            _autoLogin.value = AutoLoginData(true,result.id,result.pw)
            if(result.id != "" && result.pw != ""){
                Log.d(TAG, "재로그인 id = ${result.id} pw = ${result.pw}")
                //todo 인터넷이 없는 환경 이라서 잠시 변경
                //_loginResult.value =  LoginResultData(success = true,errorCode = 0)
                _loginResult.value = loginUseCase.login(LoginData(id =result.id, pw = result.pw))

            }
            _autoLogin.value = AutoLoginData(false)
        }

    }
    fun validLoginData(id:String,pw:String){
        _loginFormState.value = validIDAndPWD(id,pw)
    }

    fun login(id:String, pw:String){
        if(_loginFormState.value?.validID == false || _loginFormState.value?.validPW == false){
            _loginResult.value = LoginResultData(success = false,errorCode = -1)
            return
        }

        viewModelScope.launch {
            _loginResult.value = loginUseCase.login(LoginData(id =id, pw = pw))
        }

    }

    private fun validIDAndPWD(id:String, pw:String) : LoginFormStateData {
        if(!validID(id)){
            return LoginFormStateData(validID = false, validPW = false)
        }else if(!validPW(pw)){
            return LoginFormStateData(validID = true, validPW = false)
        }
        return LoginFormStateData(validID = true, validPW = true)

    }
    private fun validID(id:String):Boolean{
        return id.length >= 5
    }
    private fun validPW(pw:String):Boolean{
        return pw.length >= 8
    }

}