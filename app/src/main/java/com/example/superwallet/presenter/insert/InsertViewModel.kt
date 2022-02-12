package com.example.superwallet.presenter.insert

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superwallet.domain.model.CardData
import com.example.superwallet.domain.model.CardType
import com.example.superwallet.domain.usecase.InsertCardUseCase
import com.example.superwallet.domain.usecase.UpdateCardUseCase
import com.example.superwallet.presenter.insert.model.CardInsertFormStateData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsertViewModel @Inject constructor(private val insertCardUseCase: InsertCardUseCase, private val updateCardUseCase: UpdateCardUseCase) : ViewModel() {
    companion object{
        const val TAG = "InsertViewModel"
    }
    private val _insertFormState = MutableLiveData<CardInsertFormStateData>()
    val insertFormState : LiveData<CardInsertFormStateData> = _insertFormState

    private val _executeState = MutableLiveData<Boolean>()
    val executeState : LiveData<Boolean> = _executeState

    fun cardTitleAfterTextChanged(cardTitle :String){
        _insertFormState.value = CardInsertFormStateData(validCardTitle = validCardTitle(cardTitle), validCardCode = _insertFormState.value?.validCardCode ?: false )
    }

    fun cardCodeAfterTextChanged(cardCode :String){
        _insertFormState.value = CardInsertFormStateData(validCardTitle = _insertFormState.value?.validCardTitle?: false, validCardCode = validCardCode(cardCode) )
    }
    fun saveCard(cardTitle:String, cardCode:String , type :String){
        Log.d(TAG,"save card data : $cardTitle, $cardCode, $type")
        var cardType:CardType = CardType.BARCODE
        if(type.contains("QR")){
            cardType = CardType.QR
        }
        val cardData = CardData(cardTitle = cardTitle, cardCode = cardCode, cardType = cardType )
        viewModelScope.launch {
            insertCardUseCase.execute(cardData)
            _executeState.value = true
        }

    }
    fun updateCard(id:Int, cardTitle:String, cardCode:String , type :String){
        Log.d(TAG,"update card data : $cardTitle, $cardCode, $type")
        var cardType:CardType = CardType.BARCODE
        if(type.contains("QR")){
            cardType = CardType.QR
        }
        val cardData = CardData(id = id, cardTitle = cardTitle, cardCode = cardCode, cardType = cardType )
        viewModelScope.launch {
            updateCardUseCase.execute(cardData)
            _executeState.value = true
        }
    }

    private fun validCardCode(cardCode:String):Boolean{
        if(cardCode.isNotEmpty() && cardCode.length > 8){
            return true
        }
        return false
    }

    private fun validCardTitle(cardTitle:String):Boolean{
        if(cardTitle.isNotEmpty() && cardTitle.length > 1){
            return true
        }
        return false
    }
}