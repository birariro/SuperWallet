package com.example.superwallet.custom

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.superwallet.databinding.ActivityCardPopUpBinding
import com.example.superwallet.layer_domain.model.CardData
import com.example.superwallet.layer_domain.model.CardType
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder


class CardPopUpActivity : AppCompatActivity() {
    lateinit var binding: ActivityCardPopUpBinding

    companion object{
        const val TAG = "CardPopUpActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardPopUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intentData = intent.getSerializableExtra("item")
        if(intentData == null) {
            finish()
        }
        Log.d(TAG, "CardPopUpActivity intent data $intentData")
        val cardData = intentData as CardData
        initUI(cardData)
    }
    private fun initUI(cardData: CardData){

        val width = getDeviceWidth()
        val barcodeNumber = cardData.cardCode
        val barcodeEncoder = BarcodeEncoder()
        val bitmap = when(cardData.cardType){
            CardType.BARCODE -> barcodeEncoder.encodeBitmap(barcodeNumber, BarcodeFormat.CODE_128,width,400)
            CardType.QR -> barcodeEncoder.encodeBitmap(barcodeNumber, BarcodeFormat.QR_CODE,width,width)
        }
        binding.cardCode.setImageBitmap(bitmap)
        binding.cardTitle.text = cardData.cardTitle

    }

    private fun getDeviceWidth() :Int{
        val metrics = resources.displayMetrics
        var screenWidth = metrics.widthPixels

        Log.d(TAG, "origin width : $screenWidth")
        screenWidth = (screenWidth / 100) * 100

        Log.d(TAG, "return width : $screenWidth")
        return screenWidth
    }
}