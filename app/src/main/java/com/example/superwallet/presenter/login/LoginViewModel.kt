package com.example.superwallet.presenter.login

import androidx.lifecycle.*
import com.example.superwallet.domain.model.LoginData
import com.example.superwallet.domain.model.LoginResultData
import com.example.superwallet.domain.usecase.*
import com.example.superwallet.domain.usecase.member.FindLoginDataUseCase
import com.example.superwallet.domain.usecase.member.InsertLoginDataUseCase
import com.example.superwallet.domain.usecase.member.LoginStateUseCase
import com.example.superwallet.domain.usecase.member.LoginUseCase
import com.example.superwallet.presenter.login.model.AutoLoginData
import com.example.superwallet.presenter.login.model.LoginFormStateData
import dagger.hilt.android.lifecycle.HiltViewModel
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
    private val _loginResult = MutableLiveData<LoginResultData>()
    val loginResult :LiveData<LoginResultData> = _loginResult

    //자동 로그인 프로세스 데이터
    private val _autoLogin = MutableLiveData<AutoLoginData>()
    val autoLogin :LiveData<AutoLoginData> = _autoLogin

    private lateinit var owner: LifecycleOwner
    fun attach(activity: LoginActivity) {
        owner = activity
    }

    init {
        //저장된 로그인 정보가있다면 auto login 을 진행한다.
        viewModelScope.launch {

            var result = findLoginDataUseCase.execute()
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
            _loginResult.value = LoginResultData(success = false,errorCode = -1)
            return
        }
        val loginData = LoginData(id =id, pw = pw)
        viewModelScope.launch {
            loginUseCase.execute(loginData)

        }

        owner?.let {
            LoginStateUseCase.loginResult.observe(owner, Observer {
                    loginResultData ->
                _loginResult.value = loginResultData

                if(loginResultData.success){
                    viewModelScope.launch {
                        insertLoginDataUseCase.execute(loginData)
                    }
                }

            })
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


/*


    private fun firebaseTest(){
        val mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser

        Log.d(TAG, "firebaseTest user ${user?.email}")

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
 */