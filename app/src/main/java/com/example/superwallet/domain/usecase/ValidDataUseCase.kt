package com.example.superwallet.domain.usecase

import com.example.superwallet.domain.model.LoginFormStateData

class ValidDataUseCase {

    fun validIDAndPWD(id:String, pw:String) : LoginFormStateData{
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