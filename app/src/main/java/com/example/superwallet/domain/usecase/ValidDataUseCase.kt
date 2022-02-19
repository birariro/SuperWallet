package com.example.superwallet.domain.usecase

import android.util.Log

class ValidDataUseCase {
    enum class ValidDataType{
        id,password
    }
    fun execute(type:ValidDataType, data:String):Boolean{
        val result =  when(type){
            ValidDataType.id ->validID(data)
            ValidDataType.password -> validPW(data)
            else -> true
        }
        return result
    }
    private fun validID(id:String):Boolean{
        //이메일 형식으로 바꿔라
        return id.length >= 5
    }
    private fun validPW(pw:String):Boolean{
        return pw.length >= 4
    }

}