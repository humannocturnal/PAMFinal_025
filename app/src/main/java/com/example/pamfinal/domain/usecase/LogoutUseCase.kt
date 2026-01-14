package com.example.pamfinal.domain.usecase

import com.example.pamfinal.domain.repository.AuthRepository

class LogoutUseCase(private val repo: AuthRepository) {
    suspend operator fun invoke() = repo.clearTokens()
}
