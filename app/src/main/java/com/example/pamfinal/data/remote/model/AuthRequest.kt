package com.example.pamfinal.data.remote.model

data class LoginRequest(
    val email_hrd: String,
    val password: String
)

data class RegisterRequest(
    val nama_hrd: String,
    val email_hrd: String,
    val password: String,
    val no_wa: String? = null
)
