package com.example.superwallet.presenter.login

import android.R.attr.password
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superwallet.domain.model.LoginData
import com.example.superwallet.domain.model.LoginResultData
import com.example.superwallet.domain.usecase.FindLoginDataUseCase
import com.example.superwallet.domain.usecase.InsertLoginDataUseCase
import com.example.superwallet.domain.usecase.LoginUseCase
import com.example.superwallet.presenter.login.model.AutoLoginData
import com.example.superwallet.presenter.login.model.LoginFormStateData
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val insertLoginDataUseCase: InsertLoginDataUseCase,
    private val findLoginDataUseCase: FindLoginDataUseCase
    ) :ViewModel() {
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
        //저장된 로그인 정보가있다면 auto login 을 진행한다.
        print("view model init 호출")
        viewModelScope.launch {

            var result = findLoginDataUseCase.execute()
            _autoLogin.value = AutoLoginData(true,result.id,result.pw)
            if(result.id != "" && result.pw != ""){
                Log.d(TAG, "재로그인 id = ${result.id} pw = ${result.pw}")
                login(result.id,result.pw)
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
        val loginData = LoginData(id =id, pw = pw)
        loginUseCase.firebaseLogin(loginData){ loginResultData ->
            _loginResult.value = loginResultData
            when(loginResultData.success){
                true -> {
                    Log.d(TAG, "로그인 성공")
                    //로그인 성공이라면 저장
                    viewModelScope.launch {
                        insertLoginDataUseCase.execute(loginData)
                    }
                }
                false ->{
                    Log.d(TAG, "로그인 실패")
                    _loginResult.value = LoginResultData(success = false,errorCode = -1)
                }
            }
        }


    }

    private fun firebaseTest(){
        val mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser
        Log.d(TAG, "firebaseTest user $user")

//        mAuth.createUserWithEmailAndPassword("email1@naver.com", "password")
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    // Sign in success, update UI with the signed-in user's information
//                    Log.d(TAG, "createUserWithEmail:success")
//                    val user = mAuth.currentUser
//
//                } else {
//                    // If sign in fails, display a message to the user.
//                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
//                }
//            }


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
        //이메일 형식으로 바꿔라
        return id.length >= 5
    }
    private fun validPW(pw:String):Boolean{
        return pw.length >= 8
    }

}