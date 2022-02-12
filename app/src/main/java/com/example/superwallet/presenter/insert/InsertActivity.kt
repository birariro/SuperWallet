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
import com.example.superwallet.domain.model.CardData
import com.example.superwallet.domain.model.CardType
import com.example.superwallet.util.extension.afterTextChanged
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InsertActivity : AppCompatActivity() {
    lateinit var binding: ActivityInsertBinding
    private val viewModel: InsertViewModel by viewModels()
    lateinit var editCardData: CardData
    companion object{
        const val TAG = "InsertActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intentData = intent.getSerializableExtra("item")
        if(intentData != null) {
            Log.d(TAG, "intent data $intentData")
            editCardData = intentData as CardData
            editModeUI()
        }else{
            insertModeUI()
        }

        eventAttach()
        eventObserve()

    }
    private fun editModeUI(){
        //edit model 에서는 intent 로 받은 정보를 화면에 뿌린다.
        binding.cardTitle.setText(editCardData.cardTitle)
        binding.cardCode.setText(editCardData.cardCode)
        when(editCardData.cardType){
            CardType.BARCODE -> binding.cardTypeBarcode.isChecked = true
            CardType.QR -> binding.cardTypeQr.isChecked = true
        }
        binding.saveBtn.text = "수정"

        viewModel.cardTitleAfterTextChanged(editCardData.cardTitle)
        viewModel.cardCodeAfterTextChanged(editCardData.cardCode)

    }

    private fun insertModeUI(){
        binding.saveBtn.isEnabled = false
        binding.cardTypeBarcode.isChecked = true
        binding.saveBtn.text = "저장"
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
            when(binding.saveBtn.text){
                "저장" -> {
                    viewModel.saveCard(cardTitle.text.toString(), cardCode.text.toString(),selectTypeText)
                }
                "수정" -> {
                    viewModel.updateCard(editCardData.id, cardTitle.text.toString(), cardCode.text.toString(),selectTypeText)
                }
            }
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
        viewModel.executeState.observe(this, Observer {
            val result = it ?: return@Observer
            if(result){
                finish()
            }
        })
    }
}