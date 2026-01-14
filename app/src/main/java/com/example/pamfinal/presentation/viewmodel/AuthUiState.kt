package com.example.pamfinal.presentation.viewmodel

sealed class AuthUiState {
    object Idle : AuthUiState()
    object Loading : AuthUiState()
    data class Success(val msg: String) : AuthUiState()
    data class Error(val msg: String) : AuthUiState()
}
