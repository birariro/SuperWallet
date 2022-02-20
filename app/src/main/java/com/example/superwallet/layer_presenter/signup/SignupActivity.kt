package com.example.superwallet.layer_presenter.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.superwallet.databinding.ActivitySignupBinding
import com.example.superwallet.util.MessageUtil
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
        viewModel.signupResult.observe(this){
            val signupResult = it ?: return@observe
            if(signupResult.success){
                Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(this, MessageUtil.getMessage(signupResult.errorCode), Toast.LENGTH_SHORT).show()
            }
        }
    }
}