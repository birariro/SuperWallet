package com.example.superwallet.presenter.insert

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.superwallet.R
import com.example.superwallet.databinding.ActivityInsertBinding
import com.example.superwallet.databinding.ActivityLoginBinding
import com.example.superwallet.util.extension.afterTextChanged
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InsertActivity : AppCompatActivity() {
    lateinit var binding: ActivityInsertBinding
    private val viewModel: InsertViewModel by viewModels()
    companion object{
        const val TAG = "InsertActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        eventAttach()
        eventObserve()

    }
    private fun initUI(){
        binding.saveBtn.isEnabled = false
        binding.cardTypeBarcode.isChecked = true
    }
    private fun eventAttach(){
        val cardTitle = binding.cardTitle
        val cardCode = binding.cardCode
        cardTitle.afterTextChanged {
            viewModel.cardTitleAfterTextChanged(cardTitle.text.toString())
        }
        cardCode.afterTextChanged {
            viewModel.cardCodeAfterTextChanged(cardCode.text.toString())
        }
        binding.saveBtn.setOnClickListener {
            val selectTypeText = findViewById<RadioButton>(binding.cardTypeGroup.checkedRadioButtonId).text.toString()
            viewModel.saveCard(cardTitle.text.toString(), cardCode.text.toString(),selectTypeText)
        }
    }
    private fun eventObserve(){
        viewModel.insertFormState.observe(this, Observer {
            val result = it ?: return@Observer
            if(! result.validCardTitle || ! result.validCardCode){
                binding.saveBtn.isEnabled = false
                return@Observer
            }
            binding.saveBtn.isEnabled = true
        })
    }
}