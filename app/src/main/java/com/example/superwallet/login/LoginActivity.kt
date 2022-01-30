package com.example.superwallet.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.superwallet.R
import com.example.superwallet.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    companion object{
        const val TAG ="LoginActivity"
    }
    private lateinit var binding :ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()


    }
    private fun initUI(){
        binding.bottomLayout.resultBtn.text = "로그인"
        binding.bottomLayout.createUserBtn.setOnClickListener {
            Log.d(TAG, "회원가입 클릭")
        }
        binding.bottomLayout.findUserBtn.setOnClickListener {
            Log.d(TAG, "비밀번호 찾기 클릭")
        }
    }
}