package com.example.superwallet.presenter.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superwallet.domain.model.CommonResultData
import com.example.superwallet.domain.model.LoginData
import com.example.superwallet.domain.usecase.ValidDataUseCase
import com.example.superwallet.domain.usecase.member.SignupUseCase
import com.example.superwallet.presenter.signup.model.InputDataValidResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val signupUseCase: SignupUseCase,
    private val validDataUseCase: ValidDataUseCase):ViewModel()
{

    private val _inputIDValidResult = MutableLiveData<InputDataValidResult>()
    val inputIDValidResult : LiveData<InputDataValidResult> = _inputIDValidResult

    private val _inputPWValidResult = MutableLiveData<InputDataValidResult>()
    val inputPWValidResult : LiveData<InputDataValidResult> = _inputPWValidResult

    private val _inputRePWValidResult = MutableLiveData<InputDataValidResult>()
    val inputRePWValidResult : LiveData<InputDataValidResult> = _inputRePWValidResult

    private val _signupEnable = MutableLiveData<Boolean>()
    val signupEnable : LiveData<Boolean> = _signupEnable

    private val _signupResult = MutableLiveData<CommonResultData>()
    val signupResult : LiveData<CommonResultData> = _signupResult

    private var inputID:String = ""
    private var inputPW:String = ""
    private var inputRePW:String = ""

    fun signup(){
        val loginData = LoginData(inputID, inputPW)
        viewModelScope.launch {
            val signupResultFlow = signupUseCase.execute(loginData)
            signupResultFlow.collect {
                Log.d("SignupViewModel","signupResultFlow : $it")
                _signupResult.value = it
            }
        }

    }

    fun afterTextChangedID(id:String){
        inputID = id
        if( ! validDataUseCase.execute(ValidDataUseCase.ValidDataType.id, id)){
            _inputIDValidResult.value = InputDataValidResult(success = false, "유호하지 않는 ID")

        }else{
            _inputIDValidResult.value = InputDataValidResult(success = true, "")
        }
        validSignupState()

    }


    fun afterTextChangedPW(pw:String){
        inputPW = pw
        if( ! validDataUseCase.execute(ValidDataUseCase.ValidDataType.password, pw)){
            _inputPWValidResult.value = InputDataValidResult(success = false, "유효 하지 않는 PW")
        }else{
            _inputPWValidResult.value = InputDataValidResult(success = true, "")
        }
        validSignupState()
    }
    fun afterTextChangedRePW(rePw:String){
        inputRePW = rePw
        validSignupState()
    }

    private fun validSignupState(){
        _signupEnable.value = false
        if(_inputIDValidResult.value?.success == false || _inputPWValidResult.value?.success == false){
            return
        }
        if(inputID == "" || inputPW == "" || inputRePW == ""){
            return
        }
        if(inputPW != inputRePW){
            _inputRePWValidResult.value = InputDataValidResult(success = false, "패스워드가 일치하지않습니다.")
            return
        }else{
            _inputRePWValidResult.value = InputDataValidResult(success = true, "")
        }

        _signupEnable.value = true

    }


}