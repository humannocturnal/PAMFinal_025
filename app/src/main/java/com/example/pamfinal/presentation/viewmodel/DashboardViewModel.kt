package com.example.pamfinal.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pamfinal.domain.usecase.LogoutUseCase
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {
    fun logout() {
        viewModelScope.launch { logoutUseCase() }
    }
}
