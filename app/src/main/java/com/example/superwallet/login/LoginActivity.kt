package com.example.superwallet.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.superwallet.R
import com.example.superwallet.databinding.ActivityLoginBinding
import com.example.superwallet.util.extension.afterTextChanged
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    companion object{
        const val TAG ="LoginActivity"
    }
    private lateinit var binding :ActivityLoginBinding
    private val viewModel : LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        eventAttach()
        eventObserve()

    }
    private fun initUI(){
        binding.bottomLayout.resultBtn.text = "로그인"
        binding.bottomLayout.resultBtn.isEnabled = false

        binding.bottomLayout.createUserBtn.setOnClickListener {
            Log.d(TAG, "회원가입 클릭")
        }
        binding.bottomLayout.findUserBtn.setOnClickListener {
            Log.d(TAG, "비밀번호 찾기 클릭")
        }
    }
    private fun eventAttach(){

        val id = binding.idEditText
        val pw = binding.pwEditText
        val login_btn = binding.bottomLayout.resultBtn

        id.afterTextChanged {
            viewModel.validLoginData(id.text.toString(),pw.text.toString())
        }
        pw.afterTextChanged {
            viewModel.validLoginData(id.text.toString(),pw.text.toString())
        }
        login_btn.setOnClickListener {
            viewModel.login(id.text.toString(),pw.text.toString())
        }

    }
    private fun eventObserve(){
        viewModel.loginFormState.observe(this, Observer{
            val loginFormState = it ?: return@Observer
            binding.bottomLayout.resultBtn.isEnabled = false
            if(!loginFormState.validID){
                return@Observer
            }else if(!loginFormState.validPW){
                return@Observer
            }
            //로그인 가능한 상태
            binding.bottomLayout.resultBtn.isEnabled = true
        })
    }
}