package com.example.pamfinal.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pamfinal.domain.usecase.LoginUseCase
import com.example.pamfinal.domain.usecase.RegisterUseCase
import com.example.pamfinal.domain.usecase.SaveTokensUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
    private val saveTokensUseCase: SaveTokensUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val state: StateFlow<AuthUiState> = _state

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _state.value = AuthUiState.Loading
            val result = loginUseCase(email, password)
            result.onSuccess { tokens ->
                saveTokensUseCase(tokens)
                _state.value = AuthUiState.Success("Login berhasil")
            }.onFailure { e ->
                _state.value = AuthUiState.Error(e.message ?: "Login gagal")
            }
        }
    }

    fun register(nama: String, email: String, password: String, wa: String?) {
        viewModelScope.launch {
            _state.value = AuthUiState.Loading
            val result = registerUseCase(nama, email, password, wa)
            result.onSuccess { msg ->
                _state.value = AuthUiState.Success(msg)
            }.onFailure { e ->
                _state.value = AuthUiState.Error(e.message ?: "Register gagal")
            }
        }
    }

    fun resetState() {
        _state.value = AuthUiState.Idle
    }
}
