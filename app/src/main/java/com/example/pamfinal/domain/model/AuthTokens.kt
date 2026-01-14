package com.example.pamfinal.domain.model

data class AuthTokens(
    val accessToken: String,
    val refreshToken: String? = null,
    val role: String? = null
)
