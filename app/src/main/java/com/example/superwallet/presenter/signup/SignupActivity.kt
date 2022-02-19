package com.example.superwallet.presenter.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.superwallet.R
import com.example.superwallet.databinding.ActivityLoginBinding
import com.example.superwallet.databinding.ActivitySignupBinding
import com.example.superwallet.util.extension.afterTextChanged
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupActivity : AppCompatActivity() {
    private val viewModel:SignupViewModel by viewModels()
    lateinit var binding:ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
        eventAttach()
        eventObserve()
    }
    private fun initUI(){
        binding.resultBtn.isEnabled = false
    }
    private fun eventAttach(){
        binding.idEditText.afterTextChanged {
            viewModel.afterTextChangedID(it)
        }
        binding.pwEditText.afterTextChanged {
            viewModel.afterTextChangedPW(it)
        }
        binding.rePwEditText.afterTextChanged {
            viewModel.afterTextChangedRePW(it)
        }
        binding.resultBtn.setOnClickListener {
            viewModel.signup()
        }
    }
    private fun eventObserve(){
        viewModel.inputIDValidResult.observe(this) {

        }
        viewModel.inputPWValidResult.observe(this) {

        }
        viewModel.inputRePWValidResult.observe(this){

        }
        viewModel.signupEnable.observe(this){
            binding.resultBtn.isEnabled = false
            if(it) binding.resultBtn.isEnabled = true
        }
    }
}