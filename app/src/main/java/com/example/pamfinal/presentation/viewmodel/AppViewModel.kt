package com.example.pamfinal.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pamfinal.di.AppModule
import com.example.pamfinal.domain.model.AuthTokens
import com.example.pamfinal.domain.usecase.ObserveTokensUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AppViewModel(
    private val observeTokensUseCase: ObserveTokensUseCase
) : ViewModel() {

    private val _tokens = MutableStateFlow<AuthTokens?>(null)
    val tokens: StateFlow<AuthTokens?> = _tokens

    fun startObserveTokens(context: Context) {
        viewModelScope.launch {
            observeTokensUseCase().collect { t ->
                _tokens.value = t
                AppModule.updateTokenCache(t?.accessToken)
            }
        }
    }
}
