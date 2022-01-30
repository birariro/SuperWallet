package com.example.superwallet.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.example.superwallet.R
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

class BarcodeActivity : AppCompatActivity() {
    private val exitText :EditText by lazy { findViewById(R.id.edit_text) }
    private val barcode_image :ImageView by lazy { findViewById(R.id.barcode_image) }
    private val qr_image :ImageView by lazy { findViewById(R.id.qr_image) }
    private val btn : Button by lazy { findViewById(R.id.btn) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barcode)

        btn.setOnClickListener {
            val input = exitText.text.toString()
            val barcodeNumber = "1234 5789 123456";
            val barcodeEncoder = BarcodeEncoder()
            val bitmap = barcodeEncoder.encodeBitmap(barcodeNumber, BarcodeFormat.CODE_128,400,200)
            val bitmap2 = barcodeEncoder.encodeBitmap(barcodeNumber, BarcodeFormat.QR_CODE,400,400)
            this.barcode_image.setImageBitmap(bitmap)
            this.qr_image.setImageBitmap(bitmap2)

        }

    }
}

