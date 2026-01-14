package com.example.pamfinal.domain.usecase

import com.example.pamfinal.domain.model.AuthTokens
import com.example.pamfinal.domain.repository.AuthRepository

class SaveTokensUseCase(private val repo: AuthRepository) {
    suspend operator fun invoke(tokens: AuthTokens) = repo.saveTokens(tokens)
}
