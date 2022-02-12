package com.example.superwallet.presenter.login.model

data class AutoLoginData(
    val autoLoginProcess:Boolean,
    val id:String = "",
    val pw:String = ""
)
