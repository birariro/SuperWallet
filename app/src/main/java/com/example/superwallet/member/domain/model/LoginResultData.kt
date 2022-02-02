package com.example.superwallet.member.domain.model

data class LoginResultData(
    val success: Boolean = false,
    val errorCode: Int = -1
)
