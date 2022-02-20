package com.example.superwallet.layer_presenter.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superwallet.layer_domain.model.CommonResultData
import com.example.superwallet.layer_domain.usecase.member.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(private val logoutUseCase: LogoutUseCase): ViewModel() {
    private val _logoutResult = MutableLiveData<CommonResultData>()
    val logoutResult : LiveData<CommonResultData> = _logoutResult

    fun logout(){
        viewModelScope.launch {
            logoutUseCase.execute()
            _logoutResult.value = CommonResultData(true,0)
        }
    }
}