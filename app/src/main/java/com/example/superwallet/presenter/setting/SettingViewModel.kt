package com.example.superwallet.presenter.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superwallet.domain.usecase.member.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(private val logoutUseCase: LogoutUseCase): ViewModel() {

    fun logout(){
        viewModelScope.launch {
            logoutUseCase.execute()
        }
    }
}