package com.example.pamfinal.domain.repository

import com.example.pamfinal.domain.model.AuthTokens
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<AuthTokens>
    suspend fun register(nama: String, email: String, password: String, wa: String?): Result<String>

    suspend fun saveTokens(tokens: AuthTokens)
    fun observeTokens(): Flow<AuthTokens?>
    suspend fun clearTokens()
}
