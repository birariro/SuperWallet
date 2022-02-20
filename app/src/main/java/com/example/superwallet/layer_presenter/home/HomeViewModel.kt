package com.example.superwallet.layer_presenter.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superwallet.layer_domain.model.CardData
import com.example.superwallet.layer_domain.usecase.card.DeleteCardUseCase
import com.example.superwallet.layer_domain.usecase.card.FindAllCardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val findAllCardUseCase: FindAllCardUseCase, private val deleteCardUseCase: DeleteCardUseCase): ViewModel() {
    companion object{
        const val TAG = "HomeViewModel"
    }
    private val _cardDataList = MutableLiveData<List<CardData>>()
    val cardDataList: LiveData<List<CardData>> = _cardDataList


    init {
        getCardDataList()
    }
    private fun getCardDataList(){
        viewModelScope.launch {
            _cardDataList.value = findAllCardUseCase.execute()
        }
    }
    fun deleteCardData(cardData: CardData){
        viewModelScope.launch {
            deleteCardUseCase.execute(cardData)
            getCardDataList()
        }
    }
    fun viewOnResume(){
        getCardDataList()
    }

}