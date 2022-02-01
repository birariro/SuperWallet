package com.example.superwallet.member.data.repository

import android.util.Log
import com.example.superwallet.member.domain.repository.MemberRepository
import javax.inject.Inject
import javax.inject.Singleton


class CommonMemberRepository :MemberRepository {
    override fun login() {
        Log.d("k4keye","CommonMemberRepository!!")
    }

    override fun findID() {
        TODO("Not yet implemented")
    }

    override fun findPW() {
        TODO("Not yet implemented")
    }
}