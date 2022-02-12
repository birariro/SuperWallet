package com.example.superwallet.presenter.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superwallet.domain.model.CardData
import com.example.superwallet.domain.usecase.FindAllCardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val findAllCardUseCase: FindAllCardUseCase): ViewModel() {
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
    fun viewOnResume(){
        getCardDataList()
    }

}