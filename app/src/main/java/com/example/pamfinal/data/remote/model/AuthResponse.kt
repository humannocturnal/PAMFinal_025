package com.example.pamfinal.data.remote.model

// Sesuaikan field ini kalau API kamu beda nama
data class LoginResponse(
    val access_token: String?,
    val refresh_token: String?,
    val role: String? = null,
    val message: String? = null
)

data class RegisterResponse(
    val message: String?,
    val data: Any? = null
)

data class ErrorResponse(
    val message: String?
)
