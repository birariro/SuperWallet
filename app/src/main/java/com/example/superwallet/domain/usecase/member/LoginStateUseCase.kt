package com.example.superwallet.domain.usecase.member

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.superwallet.domain.model.CommonResultData

object LoginStateUseCase {
    //로그인 결과 데이터
    private val _loginResult = MutableLiveData<CommonResultData>()
    val loginResult : LiveData<CommonResultData> = _loginResult

    fun setLoginResult(enable:Boolean){
        Log.d("로그인상태변경","로그인상태변경 $enable")

        //같은 상태라면 변경하지 않는다.
        if(loginResult.value?.success == enable) return
        when(enable){
            true ->  _loginResult.value = CommonResultData(success = true,errorCode = 0)
            false -> _loginResult.value = CommonResultData(success = false,errorCode = -1)
        }

    }
}