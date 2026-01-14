package com.example.pamfinal.domain.usecase

import com.example.pamfinal.domain.model.AuthTokens
import com.example.pamfinal.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class ObserveTokensUseCase(private val repo: AuthRepository) {
    operator fun invoke(): Flow<AuthTokens?> = repo.observeTokens()
}
