package com.example.superwallet.presenter.login

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.superwallet.MainActivity
import com.example.superwallet.databinding.ActivityLoginBinding
import com.example.superwallet.presenter.signup.SignupActivity
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
        viewModel.attach(this)
        initUI()
        eventAttach()
        eventObserve()

    }
    private fun initUI(){
        binding.resultBtn.isEnabled = false
    }

    private fun eventAttach(){

        val id = binding.idEditText
        val pw = binding.pwEditText
        val login_btn = binding.resultBtn
        id.afterTextChanged {
            viewModel.validLoginData(id.text.toString(),pw.text.toString())
        }
        pw.afterTextChanged {
            viewModel.validLoginData(id.text.toString(),pw.text.toString())
        }
        login_btn.setOnClickListener {
            binding.loading.visibility = View.VISIBLE
            viewModel.login(id.text.toString(),pw.text.toString())

        }

        binding.createUserBtn.setOnClickListener {
            Log.d(TAG, "회원가입 클릭")
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
        binding.findUserBtn.setOnClickListener {
            Log.d(TAG, "비밀번호 찾기 클릭")
        }
    }

    private fun eventObserve(){
        //auto 로그인이 진행중일때
        viewModel.autoLogin.observe(this, Observer {
            val autoLogin = it ?:return@Observer
            if(autoLogin.autoLoginProcess){
                binding.loading.visibility = View.VISIBLE
                binding.idEditText.setText(autoLogin.id)
                binding.pwEditText.setText(autoLogin.pw)
            }else{
                binding.loading.visibility = View.GONE
            }
        })

        viewModel.loginFormState.observe(this, Observer{
            val loginFormState = it ?: return@Observer
            binding.resultBtn.isEnabled = false
            if(!loginFormState.validID || !loginFormState.validPW){
                return@Observer
            }
            //로그인 가능한 상태
            binding.resultBtn.isEnabled = true
        })

        viewModel.loginResult.observe(this, Observer {
            binding.loading.visibility = View.GONE
            val loginResult = it?: return@Observer
            if(! loginResult.success){
                Toast.makeText(this, "로그인 실패",Toast.LENGTH_SHORT).show()
                return@Observer
            }
            //로그인 성공
            Toast.makeText(this, "로그인 성공",Toast.LENGTH_SHORT).show()
            val intent = Intent(this,MainActivity::class.java)

            //로그인 성공하면 해당 페이지를 스텍에서 제거하고 페이지를 이동한다.
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD_MR1) {
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            } else {
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP;
            }

            startActivity(intent)
        })
    }
}