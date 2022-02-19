package com.example.superwallet.domain.usecase.member

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.superwallet.domain.model.CommonResultData

object SignupStateUseCase {
    //로그인 결과 데이터
    private val _signupResult = MutableLiveData<CommonResultData>()
    val signupResult : LiveData<CommonResultData> = _signupResult

    fun setSignupResult(enable:Boolean){

        //같은 상태라면 변경하지 않는다.
        if(signupResult.value?.success == enable) return
        when(enable){
            true ->  _signupResult.value = CommonResultData(success = true,errorCode = 0)
            false -> _signupResult.value = CommonResultData(success = false,errorCode = -1)
        }

    }
}