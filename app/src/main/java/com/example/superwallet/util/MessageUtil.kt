package com.example.superwallet.util

object MessageUtil {
    fun getMessage(code:Int):String{
        return when(code){
            0 -> "성공 하였습니다."
            -1001 -> "이미 사용중인 이메일 입니다."
            -1002 -> "이메일 형식이 아닙니다."
            else ->  "실패 하였습니다."
        }
    }
}